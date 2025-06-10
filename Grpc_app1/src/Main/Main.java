/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  io.grpc.BindableService
 *  io.grpc.Server
 *  io.grpc.health.v1.HealthCheckRequest
 *  io.grpc.health.v1.HealthCheckResponse
 *  io.grpc.health.v1.HealthCheckResponse$ServingStatus
 *  io.grpc.health.v1.HealthGrpc$HealthImplBase
 *  io.grpc.netty.NettyServerBuilder
 *  io.grpc.stub.StreamObserver
 */
package Main;

import Main.DirectoryChecker;
import Main.EnhancedMySqlDatabaseChecker;
import Main.PortChecker;
import Service.EmployeeServiceImpl;
import Service.FileTransferServiceImpl;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.health.v1.HealthCheckRequest;
import io.grpc.health.v1.HealthCheckResponse;
import io.grpc.health.v1.HealthGrpc;
import io.grpc.netty.NettyServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    private final Server server;
    private final Connection dbConnection;
    private final String fileStoragePath;
    private final int port;
    private final String ipAddress;

    public Main(String ipAddress, int port) throws Throwable {
        this.ipAddress = ipAddress;
        this.port = port;
        this.dbConnection = EnhancedMySqlDatabaseChecker.getValidatedConnection();
        this.fileStoragePath = DirectoryChecker.getValidDirectoryPath();
        this.server = ((NettyServerBuilder)((NettyServerBuilder)((NettyServerBuilder)NettyServerBuilder.forAddress((SocketAddress)new InetSocketAddress(ipAddress, port)).addService((BindableService)new EmployeeServiceImpl(this.dbConnection))).addService((BindableService)new FileTransferServiceImpl(this.fileStoragePath))).addService((BindableService)new HealthGrpc.HealthImplBase(){

            public void check(HealthCheckRequest request, StreamObserver<HealthCheckResponse> responseObserver) {
                responseObserver.onNext((HealthCheckResponse)HealthCheckResponse.newBuilder().setStatus(HealthCheckResponse.ServingStatus.SERVING).build());
                responseObserver.onCompleted();
            }
        })).maxInboundMessageSize(Integer.MAX_VALUE).permitKeepAliveTime(10L, TimeUnit.MINUTES).permitKeepAliveWithoutCalls(true).build();
    }

    public void start() throws IOException {
        this.server.start();
        System.out.println("Server started on " + this.ipAddress + ":" + this.port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down server on " + this.ipAddress + ":" + this.port + "...");
            this.stop();
        }));
    }

    public void stop() {
        if (this.server != null) {
            this.server.shutdown();
            try {
                if (!this.server.awaitTermination(5L, TimeUnit.SECONDS)) {
                    this.server.shutdownNow();
                }
            }
            catch (InterruptedException e) {
                this.server.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
        try {
            if (this.dbConnection != null && !this.dbConnection.isClosed()) {
                this.dbConnection.close();
                System.out.println("Database connection closed for server on " + this.ipAddress + ":" + this.port);
            }
        }
        catch (SQLException e) {
            System.err.println("Error closing database connection for server on " + this.ipAddress + ":" + this.port + ": " + e.getMessage());
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (this.server != null) {
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Throwable {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter initial IP address to check (e.g., 10.92.56.70 or localhost): ");
        String ipAddress = scanner.nextLine();
        System.out.print("Enter initial port number to check (1-65535): ");
        int port = scanner.nextInt();
        try {
            PortChecker.IpPort result = PortChecker.findFreeIpPort(ipAddress, port);
            System.out.println("Found available IP:port: " + String.valueOf(result));
            String validIp = result.getIpAddress();
            int validPort = result.getPort();
            System.out.println("Valid IP: " + validIp + ", Valid Port: " + validPort);
        }
        catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            System.exit(1);
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Main server = new Main(ipAddress, port);
            server.start();
            server.blockUntilShutdown();
        }
        catch (InterruptedException e) {
            System.err.println("Server interrupted on " + ipAddress + ":" + port + ": " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}

