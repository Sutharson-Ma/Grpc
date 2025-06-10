package Service;

import com.example.combined.grpc.*;
import com.example.combined.grpc.FileTransferServiceGrpc;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// FileTransfer is a gRPC App2 for interacting with the FileTransferService on App1, supporting file uploads, downloads, and metadata operations
public class FileTransfer {
    // gRPC channel for communication with App1
    private final ManagedChannel channel;
    // Blocking stub for synchronous gRPC calls
    private final FileTransferServiceGrpc.FileTransferServiceBlockingStub blockingStub;
    // Asynchronous stub for streaming gRPC calls
    private final FileTransferServiceGrpc.FileTransferServiceStub asyncStub;
    // Size of the current file being uploaded
    private long currentUploadFileSize = -1;
    // Scanner for reading user input from the console
    private static final Scanner scanner = new Scanner(System.in);
    // Maximum number of rename attempts for conflict resolution
    private static final int MAX_RENAME_ATTEMPTS = 5;
    
    // Constructor that takes host and port, using plaintext communication with keepalive
    public FileTransfer(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .keepAliveTime(10, TimeUnit.SECONDS)
                .keepAliveTimeout(20, TimeUnit.SECONDS)
                .keepAliveWithoutCalls(true)
                .build());
    }

    // Constructor that takes a ManagedChannel to create stubs
    public FileTransfer(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = FileTransferServiceGrpc.newBlockingStub(channel);
        asyncStub = FileTransferServiceGrpc.newStub(channel);
    }

    // Returns the blocking stub for external use
    public FileTransferServiceGrpc.FileTransferServiceBlockingStub getBlockingStub() {
        return blockingStub;
    }

    // Shuts down the gRPC channel gracefully
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    // Uploads a file to App1 using App2-streaming
    public void uploadFile(String filePathString) {
        // Convert string path to Path object
        Path filePath = Paths.get(filePathString);
        String fileName = filePath.getFileName().toString();

        // Validate file existence
        if (!Files.exists(filePath)) {
            System.err.println("App2 error: File not found: " + filePathString);
            return;
        }

        // Validate file type
        if (!Files.isRegularFile(filePath)) {
            System.err.println("App2 error: Path is not a regular file: " + filePathString);
            return;
        }

        // Latch to wait for upload completion
        final CountDownLatch finishLatch = new CountDownLatch(1);
        // Initialize SHA-256 digest for hash calculation
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Failed to initialize hash calculator on App2: " + e.getMessage());
        }

        // Extract file extension for conflict resolution prompting
        final String fileExtension;
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
            fileExtension = fileName.substring(lastDotIndex);
        } else {
            fileExtension = "";
        }

        // Generate unique session ID for this upload
        String sessionId = UUID.randomUUID().toString();

        // Holder for the request observer to allow cancellation
        final StreamObserver<FileChunk>[] requestObserverHolder = new StreamObserver[1];
        // Track rename attempts
        final int[] renameAttempts = {0};

        try {
            // Get file size for progress tracking
            currentUploadFileSize = Files.size(filePath);
            System.out.printf("Starting upload of %s (%,.2f MB)%n",
                    fileName, currentUploadFileSize / (1024.0 * 1024));

            // Set up the asynchronous upload stream
            requestObserverHolder[0] = asyncStub.uploadFile(new StreamObserver<UploadStatus>() {
                private long bytesSent = 0;

                @Override
                public void onNext(UploadStatus status) {
                    // Display App1 progress update
                    System.out.printf("\nApp1 received: %,.1f MB for %s. Conflict: %s%n",
                            status.getBytesReceived() / (1024.0 * 1024.0),
                            status.getFileName(),
                            status.getConflict());
                    System.out.flush(); // Ensure App1 update is displayed

                    // Handle App1-side file conflicts
                    if (status.getConflict() != UploadStatus.ConflictType.NONE) {
                        if (renameAttempts[0] >= MAX_RENAME_ATTEMPTS) {
                            System.err.println("Maximum rename attempts (" + MAX_RENAME_ATTEMPTS + ") reached. Aborting upload.");
                            if (requestObserverHolder[0] != null) {
                                requestObserverHolder[0].onError(new IOException("Maximum rename attempts reached"));
                            }
                            finishLatch.countDown();
                            return;
                        }
                        handleUploadConflict(status, requestObserverHolder[0], fileExtension, sessionId, renameAttempts);
                    } else if (status.getSuccess()) {
                        // Upload completed successfully
                        System.out.println("\nUpload completed successfully for: " + status.getFileName());
                        finishLatch.countDown();
                    }
                }

                @Override
                public void onError(Throwable t) {
                    // Handle App1 errors
                    Status status = Status.fromThrowable(t);
                    System.err.println("\nUpload failed: " + status.getDescription());
                    if (status.getCause() != null) {
                        status.getCause().printStackTrace();
                    }
                    finishLatch.countDown();
                }

                @Override
                public void onCompleted() {
                    // Upload stream completed
                    System.out.println("\nUpload finished successfully.");
                    requestObserverHolder[0] = null; // Prevent further use
                    finishLatch.countDown();
                }
            });

            // Read and stream file chunks
            try (FileChannel channel = FileChannel.open(filePath, StandardOpenOption.READ)) {
                ByteBuffer buffer = ByteBuffer.allocateDirect(258 * 1024);
                long totalRead = 0;
                long lastProgressUpdate = 0;
                final long PROGRESS_UPDATE_INTERVAL = 10 * 1024 * 1024; // 10 MB

                while (channel.read(buffer) != -1) {
                    buffer.flip();
                    // Check if upload was cancelled
                    if (finishLatch.getCount() == 0) {
                        System.out.println("\nUpload stream cancelled.");
                        return;
                    }

                    int bytesInChunk = buffer.remaining();
                    if (bytesInChunk == 0) {
                        buffer.clear();
                        continue; // Skip empty chunks
                    }

                    // Update hash
                    if (digest != null) {
                        digest.update(buffer.asReadOnlyBuffer());
                    }

                    totalRead += bytesInChunk; // Update totalRead before sending

                    // Send chunk to App1
                    if (requestObserverHolder[0] != null) {
                        requestObserverHolder[0].onNext(FileChunk.newBuilder()
                                .setFileName(fileName)
                                .setContent(ByteString.copyFrom(buffer))
                                .setSessionId(sessionId)
                                .build());
                    }

                    buffer.clear();

                    // Display progress every 10 MB
                    if (totalRead - lastProgressUpdate >= PROGRESS_UPDATE_INTERVAL || totalRead >= currentUploadFileSize) {
                        if (currentUploadFileSize > 0) {
                            System.out.printf("\rSent %,.2f/%,.2f MB (%.1f%%)",
                                    totalRead / (1024.0 * 1024),
                                    currentUploadFileSize / (1024.0 * 1024),
                                    (totalRead * 100.0 / currentUploadFileSize));
                        } else {
                            System.out.printf("\rSent %,.2f MB", totalRead / (1024.0 * 1024));
                        }
                        System.out.flush(); // Ensure progress is displayed
                        lastProgressUpdate = totalRead;
                    }
                }

                // Log completion of file reading
                System.out.println("\nFinished reading file for upload.");
                if (digest != null) {
                    byte[] app2Hash = digest.digest();
                    System.out.println("App2 calculated SHA-256 hash: " + bytesToHex(app2Hash));
                }

                // Complete the stream
                if (requestObserverHolder[0] != null) {
                    requestObserverHolder[0].onCompleted();
                }

            } catch (IOException e) {
                System.err.println("App2 error reading file for upload: " + e.getMessage());
                if (requestObserverHolder[0] != null) {
                    requestObserverHolder[0].onError(e);
                }
            }

        } catch (IOException e) {
            System.err.println("App2 error accessing file size or during upload initialization: " + e.getMessage());
            finishLatch.countDown();
            return;
        } catch (Exception e) {
            System.err.println("Unexpected App2 error during upload initialization: " + e.getMessage());
            e.printStackTrace();
            finishLatch.countDown();
            return;
        } finally {
            // Reset file size
            currentUploadFileSize = -1;
        }

        try {
            // Wait for upload completion (timeout after 10 minutes)
            finishLatch.await(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.err.println("App2 upload waiting interrupted.");
            Thread.currentThread().interrupt();
            if (requestObserverHolder[0] != null) {
                requestObserverHolder[0].onError(e);
            }
        }
    }

    // Handles App1-side file conflicts
    private void handleUploadConflict(UploadStatus status, StreamObserver<FileChunk> requestObserver, String fileExtension, String sessionId, int[] renameAttempts) {
        String fileName = status.getFileName();
        boolean isIdentical = status.getConflict() == UploadStatus.ConflictType.IDENTICAL;
        System.out.println("\nConflict detected on App1: " + (isIdentical ? "Identical file exists" : "Different file with same name exists") + ": " + fileName);
        System.out.println("Rename attempt " + (renameAttempts[0] + 1) + " of " + MAX_RENAME_ATTEMPTS);

        ConflictResolution.ResolutionChoice choice;
        String newFileName = null;

        // Prompt user based on conflict type
        if (isIdentical) {
            System.out.println("Options:");
            System.out.println("1. Keep existing file (abort upload)");
            System.out.println("2. Upload with a new filename");
            System.out.print("Enter choice (1-2): ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    choice = ConflictResolution.ResolutionChoice.KEEP_EXISTING;
                    break;
                case "2":
                    System.out.print("Enter new filename (include extension, e.g., 'newfile" + fileExtension + "'): ");
                    newFileName = scanner.nextLine().trim();
                    if (newFileName.isEmpty()) {
                        System.out.println("Filename cannot be empty. Aborting upload.");
                        choice = ConflictResolution.ResolutionChoice.CANCEL;
                        break;
                    }
                    if (!newFileName.endsWith(fileExtension) && !fileExtension.isEmpty()) {
                        newFileName += fileExtension;
                    }
                    choice = ConflictResolution.ResolutionChoice.RENAME;
                    renameAttempts[0]++;
                    break;
                default:
                    System.out.println("Invalid choice. Aborting upload.");
                    choice = ConflictResolution.ResolutionChoice.CANCEL;
                    break;
            }
        } else {
            System.out.println("Options:");
            System.out.println("1. Override existing file");
            System.out.println("2. Upload with a new filename");
            System.out.println("3. Cancel upload");
            System.out.print("Enter choice (1-3): ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    choice = ConflictResolution.ResolutionChoice.OVERRIDE;
                    break;
                case "2":
                    System.out.print("Enter new filename (include extension, e.g., 'newfile" + fileExtension + "'): ");
                    newFileName = scanner.nextLine().trim();
                    if (newFileName.isEmpty()) {
                        System.out.println("Filename cannot be empty. Aborting upload.");
                        choice = ConflictResolution.ResolutionChoice.CANCEL;
                        break;
                    }
                    if (!newFileName.endsWith(fileExtension) && !fileExtension.isEmpty()) {
                        newFileName += fileExtension;
                    }
                    choice = ConflictResolution.ResolutionChoice.RENAME;
                    renameAttempts[0]++;
                    break;
                case "3":
                    choice = ConflictResolution.ResolutionChoice.CANCEL;
                    break;
                default:
                    System.out.println("Invalid choice. Aborting upload.");
                    choice = ConflictResolution.ResolutionChoice.CANCEL;
                    break;
            }
        }

        try {
            // Send conflict resolution choice to App1 with a 10-second deadline
            ResolutionResponse response = blockingStub.withDeadlineAfter(10, TimeUnit.SECONDS)
                    .resolveConflict(ConflictResolution.newBuilder()
                            .setFileName(fileName)
                            .setChoice(choice)
                            .setNewFileName(newFileName != null ? newFileName : "")
                            .setSessionId(sessionId)
                            .build());
            System.out.println("Server response: " + response.getMessage());
        } catch (StatusRuntimeException e) {
            System.err.println("App2 failed to send conflict resolution: " + e.getStatus().getDescription());
            if (requestObserver != null) {
                requestObserver.onError(e);
            }
        }
    }

    // Downloads a file from App1 using App1-streaming
    public void downloadFile(String fileName, String outputFilePathString) throws IOException {
        // Convert output path to Path object
        Path outputFilePath = Paths.get(outputFilePathString);

        // Extract file extension for conflict resolution
        final String fileExtension;
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
            fileExtension = fileName.substring(lastDotIndex);
        } else {
            fileExtension = "";
        }

        // Check for local file conflicts
        if (Files.exists(outputFilePath)) {
            try {
                // Get App1 file info
                FileInfo serverFileInfo = blockingStub.getFileInfo(FileRequest.newBuilder().setFileName(fileName).build());
                byte[] app1Hash = serverFileInfo.getHash().toByteArray();
                byte[] localHash = calculateFileHash(outputFilePath);

                // Compare hashes
                boolean isIdentical = MessageDigest.isEqual(app1Hash, localHash);
                System.out.println("\nConflict detected on App2: File already exists locally at " + outputFilePath);

                if (isIdentical) {
                    // Handle identical file conflict
                    System.out.println("Local file is identical to App1 file.");
                    System.out.println("Options:");
                    System.out.println("1. Keep existing file (abort download)");
                    System.out.println("2. Download with a new filename");
                    System.out.print("Enter choice (1-2): ");
                    String input = scanner.nextLine().trim();
                    switch (input) {
                        case "1":
                            System.out.println("Download aborted.");
                            return;
                        case "2":
                            System.out.print("Enter new filename (include extension, e.g., 'newfile" + fileExtension + "'): ");
                            String newFileName = scanner.nextLine().trim();
                            if (!newFileName.endsWith(fileExtension) && !fileExtension.isEmpty()) {
                                newFileName += fileExtension;
                            }
                            outputFilePath = Paths.get(outputFilePath.getParent().toString(), newFileName);
                            break;
                        default:
                            System.out.println("Invalid choice. Aborting download.");
                            return;
                    }
                } else {
                    // Handle different file conflict
                    System.out.println("Local file is different from App1 file.");
                    System.out.println("Options:");
                    System.out.println("1. Override existing file");
                    System.out.println("2. Download with a new filename");
                    System.out.println("3. Cancel download");
                    System.out.print("Enter choice (1-3): ");
                    String input = scanner.nextLine().trim();
                    switch (input) {
                        case "1":
                            // Proceed with override
                            break;
                        case "2":
                            System.out.print("Enter new filename (include extension, e.g., 'newfile" + fileExtension + "'): ");
                            String newFileName = scanner.nextLine().trim();
                            if (!newFileName.endsWith(fileExtension) && !fileExtension.isEmpty()) {
                                newFileName += fileExtension;
                            }
                            outputFilePath = Paths.get(outputFilePath.getParent().toString(), newFileName);
                            break;
                        case "3":
                            System.out.println("Download cancelled.");
                            return;
                        default:
                            System.out.println("Invalid choice. Aborting download.");
                            return;
                    }
                }
            } catch (Exception e) {
                System.err.println("App2 error checking local file conflict: " + e.getMessage());
                return;
            }
        }

        try {
            System.out.println("Starting download for: " + fileName + " to: " + outputFilePath);

            // Open file channel for writing
            try (FileChannel fileChannel = FileChannel.open(
                    outputFilePath,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING
            )) {
                // Get file chunks from App1
                Iterator<FileChunk> chunks;
                try {
                    chunks = blockingStub.downloadFile(FileRequest.newBuilder().setFileName(fileName).build());
                } catch (StatusRuntimeException e) {
                    Status status = Status.fromThrowable(e);
                    System.err.println("App2 download failed: " + status.getDescription());
                    if (status.getCause() != null) {
                        status.getCause().printStackTrace();
                    }
                    Files.deleteIfExists(outputFilePath);
                    return;
                }

                long totalReceived = 0;
                // Initialize SHA-256 digest for hash calculation
                MessageDigest digest = null;
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException e) {
                    System.err.println("Failed to initialize hash calculator on App2 for download: " + e.getMessage());
                }

                // Process each chunk
                while (chunks.hasNext()) {
                    FileChunk chunk = chunks.next();
                    ByteString content = chunk.getContent();
                    ByteBuffer buffer = content.asReadOnlyByteBuffer();

                    // Write chunk to file
                    while (buffer.hasRemaining()) {
                        fileChannel.write(buffer);
                        totalReceived += content.size();
                        if (digest != null) {
                            digest.update(content.toByteArray());
                        }
                    }

                    // Display progress
                    System.out.printf("\rReceived %,.1f MB for %s", totalReceived / (1024.0 * 1024), fileName);
                }

                // Log completion
                System.out.println("\nDownload completed successfully for: " + fileName);
                if (digest != null) {
                    byte[] app2Hash = digest.digest();
                    System.out.println("App2 calculated SHA-256 hash: " + bytesToHex(app2Hash));
                }

            } catch (IOException e) {
                System.err.println("App2 error writing downloaded file: " + outputFilePathString);
                e.printStackTrace();
                Files.deleteIfExists(outputFilePath);
            }

        } catch (Exception e) {
            System.err.println("Unexpected App2 error during download: " + e.getMessage());
            e.printStackTrace();
            Files.deleteIfExists(outputFilePath);
        }
    }

    // Lists all files available on App1
    public void listFiles() {
        System.out.println("Requesting file list from App1...");
        try {
            // Call App1 to get file list
            FileList fileList = blockingStub.listFiles(Empty.newBuilder().build());
            List<String> files = fileList.getFileNamesList();
            if (files.isEmpty()) {
                System.out.println("No files found on App1.");
            } else {
                // Display file names
                System.out.println("Files on App1:");
                for (String fileName : files) {
                    System.out.println("- " + fileName);
                }
            }
        } catch (StatusRuntimeException e) {
            // Handle gRPC errors
            Status status = Status.fromThrowable(e);
            System.err.println("App2 failed to list files: " + status.getDescription());
            if (status.getCause() != null) {
                status.getCause().printStackTrace();
            }
        } catch (Exception e) {
            // Handle unexpected errors
            System.err.println("Unexpected App2 error listing files: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Retrieves metadata (size and hash) for a specific file
    public void getFileInfo(String fileName) {
        System.out.println("Requesting file info for: " + fileName);
        try {
            // Call App1 to get file info
            FileInfo fileInfo = blockingStub.getFileInfo(FileRequest.newBuilder().setFileName(fileName).build());
            // Display file metadata
            System.out.println("File Info for " + fileInfo.getFileName() + ":");
            System.out.printf("  Size: %,d bytes (%,.2f MB)%n", fileInfo.getSize(), fileInfo.getSize() / (1024.0 * 1024));
            System.out.println("  Hash (SHA-256): " + bytesToHex(fileInfo.getHash().toByteArray()));
        } catch (StatusRuntimeException e) {
            // Handle gRPC errors
            Status status = Status.fromThrowable(e);
            System.err.println("App2 failed to get file info for " + fileName + ": " + status.getDescription());
            if (status.getCause() != null) {
                status.getCause().printStackTrace();
            }
        } catch (Exception e) {
            // Handle unexpected errors
            System.err.println("Unexpected App2 error getting file info for " + fileName + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Calculates SHA-256 hash of a file
    private byte[] calculateFileHash(Path filePath) throws IOException {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IOException("Failed to get hash algorithm", e);
        }
        // Read file in chunks and update hash
        try (FileChannel channel = FileChannel.open(filePath, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024);
            while (channel.read(buffer) != -1) {
                buffer.flip();
                digest.update(buffer);
                buffer.clear();
            }
        }
        return digest.digest();
    }

    // Converts a byte array to a hexadecimal string
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
