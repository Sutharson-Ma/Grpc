package Service;

import com.example.combined.grpc.*;
import com.example.combined.grpc.FileTransferServiceGrpc;
import com.google.protobuf.ByteString;
import io.grpc.Status;
import io.grpc.stub.CallStreamObserver;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

// FileTransferServiceImpl implements the gRPC FileTransferService on App1 for handling file uploads, downloads, and related operations
public class FileTransferServiceImpl extends FileTransferServiceGrpc.FileTransferServiceImplBase {
    // Directory path for storing uploaded files
    private final String STORE_DIR;
    // Size of file chunks for streaming (1MB)
    private static final int CHUNK_SIZE = 1024 * 1024; // 1MB
    // Map to store futures for conflict resolution during uploads
    private final ConcurrentHashMap<String, CompletableFuture<ConflictResolution>> conflictResolutionFutures = new ConcurrentHashMap<>();

    // Constructor initializes the storage directory
    public FileTransferServiceImpl(String storagePath) {
        this.STORE_DIR = storagePath;
        initializeStorage();
    }

    // Initializes the storage directory, creating it if it doesn't exist
    private void initializeStorage() {
        try {
            Files.createDirectories(Paths.get(STORE_DIR));
            System.out.println("Storage directory initialized on App1: " + STORE_DIR);
        } catch (IOException e) {
            System.err.println("App1 failed to create storage directory: " + e.getMessage());
        }
    }

    // Handles App2-streaming file uploads
    @Override
    public StreamObserver<FileChunk> uploadFile(StreamObserver<UploadStatus> responseObserver) {
        return new StreamObserver<>() {
            // File channel for writing chunks to a temporary file
            private FileChannel fileChannel;
            // Path to the temporary file
            private Path tempFilePath;
            // Name of the file being uploaded
            private String fileName;
            // File extension (e.g., .txt)
            private String fileExtension;
            // Unique session ID for the upload
            private String sessionId;
            // Total bytes received
            private long totalReceived = 0;
            // Flush data to disk every 10MB
            private static final int FLUSH_INTERVAL = 10 * CHUNK_SIZE;
            // SHA-256 digest for computing file hash
            private MessageDigest digest;
            // Future for awaiting conflict resolution from App2
            private CompletableFuture<ConflictResolution> conflictResolutionFuture = new CompletableFuture<>();
            // Tracks if a conflict status has been sent to App2
            private boolean conflictSent = false;

            // Initialize the SHA-256 digest
            {
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException e) {
                    responseObserver.onError(Status.INTERNAL
                            .withDescription("App1 failed to initialize file hash calculator")
                            .asRuntimeException());
                }
            }

            // Processes each incoming file chunk
            @Override
            public void onNext(FileChunk chunk) {
                if (digest == null) {
                    onError(new IllegalStateException("MessageDigest was not initialized"));
                    return;
                }

                try {
                    // Initialize file channel and metadata on first chunk
                    if (fileChannel == null) {
                        fileName = chunk.getFileName();
                        sessionId = chunk.getSessionId();
                        // Validate file name
                        if (fileName == null || fileName.isEmpty()) {
                            onError(Status.INVALID_ARGUMENT.withDescription("File name is missing in the first chunk").asRuntimeException());
                            return;
                        }
                        // Validate session ID
                        if (sessionId == null || sessionId.isEmpty()) {
                            onError(Status.INVALID_ARGUMENT.withDescription("Session ID is missing in the first chunk").asRuntimeException());
                            return;
                        }

                        // Store the conflict resolution future
                        conflictResolutionFutures.put(sessionId, conflictResolutionFuture);

                        // Extract file extension
                        int lastDotIndex = fileName.lastIndexOf('.');
                        fileExtension = (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) ? fileName.substring(lastDotIndex) : "";

                        // Prevent uploading files with .tmp_upload extension
                        if (fileName.endsWith(".tmp_upload")) {
                            onError(Status.INVALID_ARGUMENT.withDescription("Cannot upload files with .tmp_upload extension").asRuntimeException());
                            return;
                        }

                        // Create temporary file path
                        tempFilePath = Paths.get(STORE_DIR, fileName + ".tmp_upload");
                        System.out.println("App1 starting upload for: " + fileName + " (session: " + sessionId + ") to temporary file: " + tempFilePath);
                        // Open file channel for writing
                        fileChannel = FileChannel.open(
                                tempFilePath,
                                StandardOpenOption.CREATE,
                                StandardOpenOption.WRITE,
                                StandardOpenOption.TRUNCATE_EXISTING
                        );
                    }

                    // Read chunk content into a buffer
                    ByteBuffer buffer = chunk.getContent().asReadOnlyByteBuffer();
                    int bytesInBuffer = buffer.remaining();
                    // Write chunk to file and update hash
                    while (buffer.hasRemaining()) {
                        int bytesWritten = fileChannel.write(buffer);
                        if (bytesWritten < 0) {
                            throw new IOException("File channel write returned negative value");
                        }
                        totalReceived += bytesWritten;
                        digest.update(chunk.getContent().toByteArray(), buffer.position() - bytesWritten, bytesWritten);
                    }

                    // Periodically flush data and send progress update
                    if (totalReceived % FLUSH_INTERVAL < bytesInBuffer) {
                        fileChannel.force(false);
                        responseObserver.onNext(UploadStatus.newBuilder()
                                .setBytesReceived(totalReceived)
                                .setFileName(fileName)
                                .setConflict(UploadStatus.ConflictType.NONE)
                                .setSuccess(false)
                                .build());
                        System.out.printf("\rApp1 received %,.1f MB for %s (session: %s)", totalReceived / (1024.0 * 1024), fileName, sessionId);
                    }
                } catch (Exception e) {
                    onError(e);
                }
            }

            // Handles errors during upload
            @Override
            public void onError(Throwable t) {
                // Clean up resources
                cleanup();
                // Remove conflict resolution future
                if (sessionId != null) {
                    conflictResolutionFutures.remove(sessionId);
                }
                // Log error details
                System.err.println("\nApp1 upload error for file: " + (fileName != null ? fileName : "unknown") + " (session: " + (sessionId != null ? sessionId : "unknown") + ")");
                if (t != null) {
                    t.printStackTrace();
                }
                // Construct error message
                String errorMessage = "App1 error during upload";
                if (t != null) {
                    errorMessage += ": " + (t.getMessage() != null ? t.getMessage() : t.getClass().getSimpleName());
                }
                // Send error to App2
                responseObserver.onError(Status.INTERNAL
                        .withDescription(errorMessage)
                        .withCause(t)
                        .asRuntimeException());
            }

            // Completes the upload process
            @Override
            public void onCompleted() {
                try {
                    if (fileChannel != null && fileChannel.isOpen()) {
                        // Finalize file write
                        fileChannel.force(true);
                        fileChannel.close();
                        System.out.println("\nApp1 temporary file closed: " + tempFilePath);

                        // Compute final hash of uploaded file
                        Path finalFilePath = Paths.get(STORE_DIR, fileName);
                        byte[] newFileHash = digest.digest();

                        // Maximum number of rename attempts to prevent infinite loops
                        final int MAX_RENAME_ATTEMPTS = 5;
                        int renameAttempts = 0;

                        // Check for existing file and handle conflicts
                        while (Files.exists(finalFilePath) && renameAttempts < MAX_RENAME_ATTEMPTS) {
                            System.out.println("File already exists on App1: " + fileName);
                            byte[] existingFileHash = calculateFileHash(finalFilePath);

                            UploadStatus.ConflictType conflictType;
                            // Compare hashes to determine conflict type
                            if (MessageDigest.isEqual(newFileHash, existingFileHash)) {
                                conflictType = UploadStatus.ConflictType.IDENTICAL;
                                System.out.println("Uploaded file is identical to existing file on App1: " + fileName);
                            } else {
                                conflictType = UploadStatus.ConflictType.DIFFERENT;
                                System.out.println("Uploaded file is different from existing file on App1: " + fileName);
                            }

                            // Notify App2 of conflict
                            responseObserver.onNext(UploadStatus.newBuilder()
                                    .setConflict(conflictType)
                                    .setFileName(fileName)
                                    .setBytesReceived(totalReceived)
                                    .setSuccess(false)
                                    .build());
                            conflictSent = true;

                            // Create a new future for conflict resolution
                            conflictResolutionFuture = new CompletableFuture<>();
                            conflictResolutionFutures.put(sessionId, conflictResolutionFuture);

                            // Wait for App2's conflict resolution choice (timeout after 30 seconds)
                            try {
                                ConflictResolution resolution = conflictResolutionFuture.get(30, TimeUnit.SECONDS);

                                // Handle resolution choice
                                switch (resolution.getChoice()) {
                                    case KEEP_EXISTING:
                                        System.out.println("App2 chose to keep existing file: " + fileName);
                                        Files.deleteIfExists(tempFilePath);
                                        responseObserver.onCompleted();
                                        return;
                                    case CANCEL:
                                        System.out.println("App2 chose to cancel upload: " + fileName);
                                        Files.deleteIfExists(tempFilePath);
                                        responseObserver.onCompleted();
                                        return;
                                    case OVERRIDE:
                                        System.out.println("App2 chose to override existing file: " + fileName);
                                        Files.move(tempFilePath, finalFilePath, StandardCopyOption.REPLACE_EXISTING);
                                        break;
                                    case RENAME:
                                        String newFileName = resolution.getNewFileName();
                                        if (newFileName == null || newFileName.isEmpty()) {
                                            throw new IOException("New file name is empty");
                                        }

                                        // Append original extension if needed
                                        if (!newFileName.endsWith(fileExtension) && !fileExtension.isEmpty()) {
                                            newFileName += fileExtension;
                                        }

                                        // Update fileName and finalFilePath for the next iteration
                                        fileName = newFileName;
                                        finalFilePath = Paths.get(STORE_DIR, newFileName);
                                        renameAttempts++;
                                        continue; // Check if the new file name exists
                                    default:
                                        throw new IOException("Unknown resolution choice: " + resolution.getChoice());
                                }
                                // If we reach here, OVERRIDE was chosen, so break the loop
                                break;
                            } catch (java.util.concurrent.TimeoutException e) {
                                System.err.println("App1 timeout waiting for conflict resolution for file: " + fileName);
                                throw new IOException("Client did not respond to conflict resolution within 30 seconds");
                            }
                        }

                        // If file still exists after max rename attempts, fail the upload
                        if (Files.exists(finalFilePath)) {
                            throw new IOException("Failed to resolve file name conflicts after " + MAX_RENAME_ATTEMPTS + " attempts for file: " + fileName);
                        }

                        // No conflict or conflict resolved, move temporary file to final location
                        System.out.println("App1 moving temporary file " + tempFilePath + " to final location " + finalFilePath);
                        Files.move(tempFilePath, finalFilePath, StandardCopyOption.REPLACE_EXISTING);

                        // Send success response to App2
                        responseObserver.onNext(UploadStatus.newBuilder()
                                .setBytesReceived(totalReceived)
                                .setSuccess(true)
                                .setFileName(fileName)
                                .setConflict(UploadStatus.ConflictType.NONE)
                                .build());
                        responseObserver.onCompleted();
                        System.out.println("App1 upload completed successfully for: " + fileName);
                    } else {
                        // No data received, complete the stream
                        responseObserver.onCompleted();
                    }
                } catch (Exception e) {
                    // Log and handle errors during finalization
                    System.err.println("\nApp1 error during upload finalization for file: " + (fileName != null ? fileName : "unknown"));
                    e.printStackTrace();
                    cleanup();
                    responseObserver.onError(Status.INTERNAL
                            .withDescription("App1 error finalizing upload: " + (e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName()))
                            .withCause(e)
                            .asRuntimeException());
                } finally {
                    // Remove conflict resolution future
                    if (sessionId != null) {
                        conflictResolutionFutures.remove(sessionId);
                    }
                }
            }

            // Cleans up resources (file channel and temporary file)
            private void cleanup() {
                System.out.println("App1 cleaning up upload resources for file: " + (fileName != null ? fileName : "unknown"));
                try {
                    if (fileChannel != null && fileChannel.isOpen()) {
                        fileChannel.close();
                    }
                } catch (IOException e) {
                    System.err.println("App1 error closing file channel during cleanup: " + e.getMessage());
                }
                if (tempFilePath != null) {
                    try {
                        Files.deleteIfExists(tempFilePath);
                    } catch (IOException e) {
                        System.err.println("App1 error deleting temporary file during cleanup: " + e.getMessage());
                    }
                }
            }
        };
    }

    // Processes conflict resolution choices from App2
    @Override
    public void resolveConflict(ConflictResolution request, StreamObserver<ResolutionResponse> responseObserver) {
        try {
            String sessionId = request.getSessionId();
            // Validate session ID
            if (sessionId == null || sessionId.isEmpty()) {
                responseObserver.onError(Status.INVALID_ARGUMENT
                        .withDescription("Session ID is missing in conflict resolution")
                        .asRuntimeException());
                return;
            }

            // Retrieve the conflict resolution future
            CompletableFuture<ConflictResolution> future = conflictResolutionFutures.get(sessionId);
            if (future == null) {
                responseObserver.onError(Status.NOT_FOUND
                        .withDescription("No active upload found for session: " + sessionId)
                        .asRuntimeException());
                return;
            }

            // Complete the future with App2's resolution choice
            future.complete(request);
            responseObserver.onNext(ResolutionResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("App1 received conflict resolution for session: " + sessionId)
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            // Handle unexpected errors
            responseObserver.onError(Status.INTERNAL
                    .withDescription("App1 failed to process conflict resolution: " + e.getMessage())
                    .asRuntimeException());
        }
    }

    // Handles App1-streaming file downloads
    @Override
    public void downloadFile(FileRequest request, StreamObserver<FileChunk> responseObserver) {
        String fileName = request.getFileName();
        // Validate file name
        if (fileName == null || fileName.isEmpty()) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("File name is missing in download request").asRuntimeException());
            return;
        }

        Path filePath = Paths.get(STORE_DIR, fileName);

        // Check if file exists
        if (!Files.exists(filePath)) {
            System.err.println("App1 download failed: File not found: " + fileName);
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("File not found on App1: " + fileName)
                    .asRuntimeException());
            return;
        }

        // Check if file is a regular file
        if (!Files.isRegularFile(filePath)) {
            System.err.println("App1 download failed: Path is not a regular file: " + fileName);
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Path is not a regular file on App1: " + fileName)
                    .asRuntimeException());
            return;
        }

        // Get gRPC context for cancellation handling
        io.grpc.Context context = io.grpc.Context.current();

        try {
            long fileSize = Files.size(filePath);
            System.out.printf("App1 starting download: %s (%,.2f MB)%n",
                    fileName, fileSize / (1024.0 * 1024));

            // Open file channel for reading
            try (FileChannel channel = FileChannel.open(filePath, StandardOpenOption.READ)) {
                ByteBuffer buffer = ByteBuffer.allocateDirect(CHUNK_SIZE);
                long bytesSent = 0;
                long lastUpdate = System.currentTimeMillis();
                final CallStreamObserver<FileChunk> callResponseObserver = (CallStreamObserver<FileChunk>) responseObserver;

                // Read file in chunks and stream to App2
                while (channel.read(buffer) != -1) {
                    buffer.flip();

                    // Wait for App2 to be ready (flow control)
                    while (!callResponseObserver.isReady()) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(5);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.err.println("App1 download interrupted for file: " + fileName);
                            if (context.isCancelled()) {
                                System.out.println("App1 download cancelled by App2 due to interruption for file: " + fileName);
                                return;
                            }
                            throw new IOException("Download interrupted", e);
                        }
                        if (context.isCancelled()) {
                            System.out.println("App1 download cancelled by App2 during flow control wait for file: " + fileName);
                            return;
                        }
                    }

                    // Check for cancellation
                    if (context.isCancelled()) {
                        System.out.println("App1 download cancelled by App2 before sending chunk for file: " + fileName);
                        return;
                    }

                    // Send chunk to App2
                    int bytesToSend = buffer.remaining();
                    callResponseObserver.onNext(FileChunk.newBuilder()
                            .setContent(ByteString.copyFrom(buffer))
                            .setFileName(fileName)
                            .build());

                    bytesSent += bytesToSend;
                    buffer.clear();

                    // Log progress periodically
                    long now = System.currentTimeMillis();
                    if ((bytesSent % (10 * 1024 * 1024) < bytesToSend) || (now - lastUpdate > 1000)) {
                        if (fileSize > 0) {
                            System.out.printf("\rApp1 sent %,.1f/%,.1f MB (%.1f%%) for %s",
                                    bytesSent / (1024.0 * 1024),
                                    fileSize / (1024.0 * 1024),
                                    (bytesSent * 100.0 / fileSize),
                                    fileName);
                        } else {
                            System.out.printf("\rApp1 sent %,d bytes for %s", bytesSent, fileName);
                        }
                        lastUpdate = now;
                    }
                }
                System.out.println("\nApp1 finished reading file for download: " + fileName);
                callResponseObserver.onCompleted();
                System.out.println("App1 download completed successfully for: " + fileName);
            } catch (IOException e) {
                System.err.println("App1 error reading file for download: " + fileName);
                e.printStackTrace();
                if (context.isCancelled()) {
                    System.out.println("App1 download stream ended due to App2 cancellation for file: " + fileName);
                } else {
                    responseObserver.onError(Status.INTERNAL
                            .withDescription("App1 error reading file for download: " + (e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName()))
                            .withCause(e)
                            .asRuntimeException());
                }
            }
        } catch (Exception e) {
            System.err.println("App1 unexpected error during download for file: " + fileName);
            e.printStackTrace();
            responseObserver.onError(Status.INTERNAL
                    .withDescription("App1 unexpected error during download: " + (e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName()))
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    // Lists all files in the storage directory
    @Override
    public void listFiles(Empty request, StreamObserver<FileList> responseObserver) {
        try {
            System.out.println("App1 listing files in storage directory: " + STORE_DIR);
            // Collect names of regular files, excluding temporary upload files
            FileList files = FileList.newBuilder()
                    .addAllFileNames(Files.list(Paths.get(STORE_DIR))
                            .filter(Files::isRegularFile)
                            .filter(p -> !p.getFileName().toString().endsWith(".tmp_upload"))
                            .map(Path::getFileName)
                            .map(Path::toString)
                            .collect(Collectors.toList()))
                    .build();
            System.out.println("App1 found " + files.getFileNamesCount() + " files.");
            // Send file list to App2
            responseObserver.onNext(files);
            responseObserver.onCompleted();
        } catch (IOException e) {
            System.err.println("App1 failed to list files:");
            e.printStackTrace();
            responseObserver.onError(Status.INTERNAL
                    .withDescription("App1 failed to list files: " + (e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName()))
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    // Retrieves metadata (size and hash) for a specific file
    @Override
    public void getFileInfo(FileRequest request, StreamObserver<FileInfo> responseObserver) {
        String fileName = request.getFileName();
        // Validate file name
        if (fileName == null || fileName.isEmpty()) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("File name is missing in file info request").asRuntimeException());
            return;
        }

        Path filePath = Paths.get(STORE_DIR, fileName);

        // Check if file exists and is a regular file
        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            System.err.println("App1 file info request failed: File not found or is not a regular file: " + fileName);
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("File not found on App1: " + fileName)
                    .asRuntimeException());
            return;
        }

        try {
            // Get file size and hash
            long fileSize = Files.size(filePath);
            byte[] fileHash = calculateFileHash(filePath);

            // Send file info to App2
            responseObserver.onNext(FileInfo.newBuilder()
                    .setFileName(fileName)
                    .setSize(fileSize)
                    .setHash(ByteString.copyFrom(fileHash))
                    .build());
            responseObserver.onCompleted();
            System.out.println("App1 sent file info for: " + fileName);
        } catch (IOException e) {
            System.err.println("App1 failed to get file info for: " + fileName);
            e.printStackTrace();
            responseObserver.onError(Status.INTERNAL
                    .withDescription("App1 failed to get file info: " + (e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName()))
                    .asRuntimeException());
        } catch (Exception e) {
            System.err.println("App1 unexpected error getting file info for: " + fileName);
            e.printStackTrace();
            responseObserver.onError(Status.INTERNAL
                    .withDescription("App1 unexpected error getting file info: " + (e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName()))
                    .asRuntimeException());
        }
    }

    // Calculates SHA-256 hash of a file
    private byte[] calculateFileHash(Path filePath) throws IOException {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IOException("App1 failed to get hash algorithm", e);
        }

        // Read file in chunks and update hash
        try (FileChannel channel = FileChannel.open(filePath, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(CHUNK_SIZE);
            while (channel.read(buffer) != -1) {
                buffer.flip();
                digest.update(buffer);
                buffer.clear();
            }
        }
        return digest.digest();
    }
}