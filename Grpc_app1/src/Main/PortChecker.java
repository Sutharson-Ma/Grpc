package Main;

import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PortChecker {
    private static final int MIN_PORT = 1;
    private static final int MAX_PORT = 65535;
    private static final int DEFAULT_TIMEOUT = 3000;

    public static IpPort findFreeIpPort(String initialIp, int initialPort) throws Throwable {
        Scanner scanner = new Scanner(System.in);
        String ipAddress = initialIp;
        int port = initialPort;

        while (true) {
            try {
                ipAddress = validateIpAddress(ipAddress);
                port = validatePort(String.valueOf(port));
                if (!isServerRunning(ipAddress, port, DEFAULT_TIMEOUT)) {
                    System.out.println("No server is running on " + ipAddress + ":" + port + ". This combination is available.");
                    return new IpPort(ipAddress, port);
                }
                System.out.println("A server is running on " + ipAddress + ":" + port + ". Please provide a different IP and port.");
                System.out.print("Enter IP address to check (e.g., 10.92.56.70 or localhost, or 'exit' to quit): ");
                ipAddress = scanner.nextLine().trim();
                if (ipAddress.equalsIgnoreCase("exit")) {
                    throw new IllegalStateException("User chose to exit without selecting a free IP and port.");
                }
                System.out.print("Enter port number to check (1-65535): ");
                String portInput = scanner.nextLine().trim();
                port = Integer.parseInt(portInput); // Will be validated in the next iteration
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }

    private static String validateIpAddress(String ipAddress) {
        if (ipAddress == null || ipAddress.trim().isEmpty()) {
            throw new IllegalArgumentException("IP address cannot be empty");
        }
        ipAddress = ipAddress.trim();
        if (ipAddress.equals("localhost") || ipAddress.equals("127.0.0.1")) {
            return ipAddress;
        }
        try {
            // Validate IP address format using InetAddress
            InetAddress.getByName(ipAddress);
            if (!ipAddress.matches("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")) {
                throw new IllegalArgumentException("Invalid IP address format");
            }
            return ipAddress;
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("Invalid IP address: " + e.getMessage());
        }
    }

    private static int validatePort(String portStr) {
        try {
            int port = Integer.parseInt(portStr.trim());
            if (port < MIN_PORT || port > MAX_PORT) {
                throw new IllegalArgumentException("Port must be between " + MIN_PORT + " and " + MAX_PORT);
            }
            return port;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid port number format");
        }
    }

    private static boolean isServerRunning(String ipAddress, int port, int timeout) throws Throwable {
        System.out.println("Checking for server at " + ipAddress + ":" + port + "...");
        try (Socket socket = new Socket()) {
            socket.connect(new java.net.InetSocketAddress(ipAddress, port), timeout);
            System.out.println("Server is running on " + ipAddress + ":" + port);
            return true;
        } catch (ConnectException e) {
            System.out.println("No server found on " + ipAddress + ":" + port + ". Connection refused.");
            return false;
        } catch (UnknownHostException e) {
            System.out.println("No server found on " + ipAddress + ":" + port + ". Unknown host.");
            return false;
        } catch (java.io.IOException e) {
            System.out.println("No server found on " + ipAddress + ":" + port + ". Error: " + e.getMessage());
            return false;
        }
    }

    public static class IpPort {
        private final String ipAddress;
        private final int port;

        public IpPort(String ipAddress, int port) {
            this.ipAddress = ipAddress;
            this.port = port;
        }

        public String getIpAddress() {
            return this.ipAddress;
        }

        public int getPort() {
            return this.port;
        }

        public String toString() {
            return this.ipAddress + ":" + this.port;
        }
    }
}