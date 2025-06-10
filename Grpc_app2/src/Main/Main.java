package Main;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.example.combined.grpc.Empty;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.InputMismatchException;
import Service.EmployeeRequest;
import Service.FileTransfer;

public class Main {
    public static void main(String[] args) {
        String host = "10.92.58.8";
        int port = 50050;
        if (args.length > 0) {
            host = args[0];
        }
        if (args.length > 1) {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number provided: " + args[1]);
                System.err.println("Using default port " + port);
            }
        }

        ManagedChannel channel = null;
        EmployeeRequest employeeClient = null;
        FileTransfer fileTransferClient = null;
        Scanner scanner = null;

        try {
            channel = ManagedChannelBuilder.forAddress(host, port)
                    .usePlaintext()
                    .maxInboundMessageSize(Integer.MAX_VALUE)
                    .maxInboundMetadataSize(Integer.MAX_VALUE)
                    .keepAliveTime(30, TimeUnit.SECONDS)
                    .keepAliveTimeout(10, TimeUnit.SECONDS)
                    .enableRetry()
                    .executor(Executors.newFixedThreadPool(4))
                    .build();

            employeeClient = new EmployeeRequest(host,port);
            fileTransferClient = new FileTransfer(channel);
            scanner = new Scanner(System.in);

           

            while (true) {
            	 System.out.println("╔══════════════════════════════════╗");
                 System.out.println("║   COMBINED gRPC CLIENT (App2)    ║");
                 System.out.println("╚══════════════════════════════════╝");
                 System.out.println("Connected to server at " + host + ":" + port);
                 System.out.println("\nAvailable options:");
                 System.out.println("1. Employee Operations");
                 System.out.println("2. File Transfer Operations");
                 System.out.println("3. Exit");
                System.out.print("\nEnter option (1-3): ");
                String input = scanner.nextLine().trim();
                int choice;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 3.");
                    continue;
                }

                try {
                    switch (choice) {
                        case 1:
                            handleEmployeeOperations(employeeClient, scanner);
                            break;
                        case 2:
                            handleFileTransferOperations(fileTransferClient, scanner);
                            break;
                        case 3:
                            System.out.println("Exiting application...");
                            return;
                        default:
                            System.out.println("Invalid option. Please choose 1, 2, or 3.");
                            break;
                    }
                } catch (Exception e) {
                    System.err.println("An unexpected error occurred: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred during client initialization: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (channel != null) {
                shutdownChannel(channel);
            }
        }
    }

    private static void handleEmployeeOperations(EmployeeRequest client, Scanner scanner) {
        int empChoice;
        do {
            client.displayMenu();
            System.out.print("\nEnter choice (0-13): ");
            try {
                empChoice = Integer.parseInt(scanner.nextLine());
                if (empChoice != 0) {
                    client.handleUserChoice(empChoice);
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid number.");
                empChoice = -1;
            } catch (Exception e) {
                System.err.println("Error during employee operation: " + e.getMessage());
                empChoice = -1;
            }
        } while (empChoice != 0);
    }

    private static void handleFileTransferOperations(FileTransfer client, Scanner scanner) {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║      FILE TRANSFER OPERATIONS    ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.println("Available commands:");
        System.out.println("  list    - Show available files on server");
        System.out.println("  upload  - Upload a file to server");
        System.out.println("  download - Download a file from server");
        System.out.println("  exit    - Return to main menu");

        while (true) {
            System.out.print("\nEnter command (list/upload/download/exit): ");
            String command = scanner.nextLine().trim().toLowerCase();

            try {
                switch (command) {
                    case "list":
                        System.out.println("\nFetching file list from server...");
                        client.listFiles();
                        break;

                    case "upload":
                        handleUpload(client, scanner);
                        break;

                    case "download":
                        handleDownload(client, scanner);
                        break;

                    case "exit":
                        System.out.println("Returning to main menu...");
                        return;

                    default:
                        System.out.println("Invalid command. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.err.println("An unexpected error occurred during command execution: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void handleUpload(FileTransfer client, Scanner scanner) {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║           FILE UPLOAD            ║");
        System.out.println("╚══════════════════════════════════╝");

        System.out.print("Enter path of file to upload: ");
        String localFilePath = scanner.nextLine().trim();

        File fileToUpload = new File(localFilePath);
        if (!fileToUpload.exists() || !fileToUpload.isFile()) {
            System.err.println("Error: File not found or is not a regular file: " + localFilePath);
            return;
        }

        System.out.println("\nStarting upload of: " + fileToUpload.getName());
        System.out.printf("File size: %,.2f MB\n", fileToUpload.length() / (1024.0 * 1024));

        try {
            client.uploadFile(localFilePath);
        } catch (Exception e) {
            System.err.println("Upload failed: " + e.getMessage());
        }
    }

    private static void handleDownload(FileTransfer client, Scanner scanner) {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║          FILE DOWNLOAD           ║");
        System.out.println("╚══════════════════════════════════╝");

        System.out.println("Fetching available files from server...");

        List<String> availableFiles;
        try {
            availableFiles = client.getBlockingStub()
                    .listFiles(Empty.newBuilder().build())
                    .getFileNamesList();
        } catch (StatusRuntimeException e) {
            System.err.println("Failed to retrieve file list: " + e.getStatus());
            return;
        } catch (Exception e) {
            System.err.println("Error retrieving file list: " + e.getMessage());
            return;
        }

        if (availableFiles.isEmpty()) {
            System.out.println("No files available for download on server.");
            return;
        }

        System.out.println("\nAvailable files on server:");
        for (int i = 0; i < availableFiles.size(); i++) {
            System.out.printf("%2d. %s\n", i + 1, availableFiles.get(i));
        }

        int fileNumber = -1;
        while (fileNumber < 0 || fileNumber > availableFiles.size()) {
            System.out.print("\nEnter file number to download (0 to cancel): ");
            try {
                fileNumber = scanner.nextInt();
                scanner.nextLine();

                if (fileNumber == 0) {
                    System.out.println("Download cancelled.");
                    return;
                }

                if (fileNumber < 1 || fileNumber > availableFiles.size()) {
                    System.out.println("Please enter a number between 1 and " + availableFiles.size());
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        String remoteFileName = availableFiles.get(fileNumber - 1);
        System.out.print("Enter directory path to save the file: ");
        String saveDir = scanner.nextLine().trim();

        File saveDirFile = new File(saveDir);
        if (!saveDirFile.exists()) {
            System.out.print("Directory '" + saveDir + "' does not exist. Create it? (yes/no): ");
            String createDirResponse = scanner.nextLine().trim().toLowerCase();
            if (createDirResponse.equals("yes")) {
                if (saveDirFile.mkdirs()) {
                    System.out.println("Directory created: " + saveDirFile.getAbsolutePath());
                } else {
                    System.err.println("Failed to create directory: " + saveDir);
                    return;
                }
            } else {
                System.out.println("Download cancelled.");
                return;
            }
        } else if (!saveDirFile.isDirectory()) {
            System.err.println("Error: Path is not a directory: " + saveDir);
            return;
        }

        System.out.print("Enter filename to save as (with extension, press Enter to use original name): ");
        String desiredFileName = scanner.nextLine().trim();
        if (desiredFileName.isEmpty()) {
            desiredFileName = remoteFileName;
        } else {
            String remoteExtension = "";
            int lastDotIndex = remoteFileName.lastIndexOf('.');
            if (lastDotIndex != -1 && lastDotIndex < remoteFileName.length() - 1) {
                remoteExtension = remoteFileName.substring(lastDotIndex);
            }
            if (!desiredFileName.endsWith(remoteExtension)) {
                desiredFileName += remoteExtension;
            }
        }

        Path localSavePath = Paths.get(saveDir, desiredFileName);

        System.out.println("\nStarting download of: " + remoteFileName);
        try {
            client.downloadFile(remoteFileName, localSavePath.toString());
        } catch (Exception e) {
            System.err.println("Download failed: " + e.getMessage());
        }
    }

    private static void shutdownChannel(ManagedChannel channel) {
        System.out.println("\nShutting down connection to server...");
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("Connection closed successfully.");
        } catch (InterruptedException e) {
            System.err.println("Error while shutting down connection: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}