package com.example.combined.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * FileTransferService
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.70.0)",
    comments = "Source: Schema.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FileTransferServiceGrpc {

  private FileTransferServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "FileTransferService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.FileChunk,
      com.example.combined.grpc.UploadStatus> getUploadFileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UploadFile",
      requestType = com.example.combined.grpc.FileChunk.class,
      responseType = com.example.combined.grpc.UploadStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.FileChunk,
      com.example.combined.grpc.UploadStatus> getUploadFileMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.FileChunk, com.example.combined.grpc.UploadStatus> getUploadFileMethod;
    if ((getUploadFileMethod = FileTransferServiceGrpc.getUploadFileMethod) == null) {
      synchronized (FileTransferServiceGrpc.class) {
        if ((getUploadFileMethod = FileTransferServiceGrpc.getUploadFileMethod) == null) {
          FileTransferServiceGrpc.getUploadFileMethod = getUploadFileMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.FileChunk, com.example.combined.grpc.UploadStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UploadFile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.FileChunk.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.UploadStatus.getDefaultInstance()))
              .setSchemaDescriptor(new FileTransferServiceMethodDescriptorSupplier("UploadFile"))
              .build();
        }
      }
    }
    return getUploadFileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.ConflictResolution,
      com.example.combined.grpc.ResolutionResponse> getResolveConflictMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ResolveConflict",
      requestType = com.example.combined.grpc.ConflictResolution.class,
      responseType = com.example.combined.grpc.ResolutionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.ConflictResolution,
      com.example.combined.grpc.ResolutionResponse> getResolveConflictMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.ConflictResolution, com.example.combined.grpc.ResolutionResponse> getResolveConflictMethod;
    if ((getResolveConflictMethod = FileTransferServiceGrpc.getResolveConflictMethod) == null) {
      synchronized (FileTransferServiceGrpc.class) {
        if ((getResolveConflictMethod = FileTransferServiceGrpc.getResolveConflictMethod) == null) {
          FileTransferServiceGrpc.getResolveConflictMethod = getResolveConflictMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.ConflictResolution, com.example.combined.grpc.ResolutionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ResolveConflict"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.ConflictResolution.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.ResolutionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FileTransferServiceMethodDescriptorSupplier("ResolveConflict"))
              .build();
        }
      }
    }
    return getResolveConflictMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.FileRequest,
      com.example.combined.grpc.FileChunk> getDownloadFileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DownloadFile",
      requestType = com.example.combined.grpc.FileRequest.class,
      responseType = com.example.combined.grpc.FileChunk.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.FileRequest,
      com.example.combined.grpc.FileChunk> getDownloadFileMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.FileRequest, com.example.combined.grpc.FileChunk> getDownloadFileMethod;
    if ((getDownloadFileMethod = FileTransferServiceGrpc.getDownloadFileMethod) == null) {
      synchronized (FileTransferServiceGrpc.class) {
        if ((getDownloadFileMethod = FileTransferServiceGrpc.getDownloadFileMethod) == null) {
          FileTransferServiceGrpc.getDownloadFileMethod = getDownloadFileMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.FileRequest, com.example.combined.grpc.FileChunk>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DownloadFile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.FileRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.FileChunk.getDefaultInstance()))
              .setSchemaDescriptor(new FileTransferServiceMethodDescriptorSupplier("DownloadFile"))
              .build();
        }
      }
    }
    return getDownloadFileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.Empty,
      com.example.combined.grpc.FileList> getListFilesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListFiles",
      requestType = com.example.combined.grpc.Empty.class,
      responseType = com.example.combined.grpc.FileList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.Empty,
      com.example.combined.grpc.FileList> getListFilesMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.Empty, com.example.combined.grpc.FileList> getListFilesMethod;
    if ((getListFilesMethod = FileTransferServiceGrpc.getListFilesMethod) == null) {
      synchronized (FileTransferServiceGrpc.class) {
        if ((getListFilesMethod = FileTransferServiceGrpc.getListFilesMethod) == null) {
          FileTransferServiceGrpc.getListFilesMethod = getListFilesMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.Empty, com.example.combined.grpc.FileList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListFiles"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.FileList.getDefaultInstance()))
              .setSchemaDescriptor(new FileTransferServiceMethodDescriptorSupplier("ListFiles"))
              .build();
        }
      }
    }
    return getListFilesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.combined.grpc.FileRequest,
      com.example.combined.grpc.FileInfo> getGetFileInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetFileInfo",
      requestType = com.example.combined.grpc.FileRequest.class,
      responseType = com.example.combined.grpc.FileInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.combined.grpc.FileRequest,
      com.example.combined.grpc.FileInfo> getGetFileInfoMethod() {
    io.grpc.MethodDescriptor<com.example.combined.grpc.FileRequest, com.example.combined.grpc.FileInfo> getGetFileInfoMethod;
    if ((getGetFileInfoMethod = FileTransferServiceGrpc.getGetFileInfoMethod) == null) {
      synchronized (FileTransferServiceGrpc.class) {
        if ((getGetFileInfoMethod = FileTransferServiceGrpc.getGetFileInfoMethod) == null) {
          FileTransferServiceGrpc.getGetFileInfoMethod = getGetFileInfoMethod =
              io.grpc.MethodDescriptor.<com.example.combined.grpc.FileRequest, com.example.combined.grpc.FileInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetFileInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.FileRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.combined.grpc.FileInfo.getDefaultInstance()))
              .setSchemaDescriptor(new FileTransferServiceMethodDescriptorSupplier("GetFileInfo"))
              .build();
        }
      }
    }
    return getGetFileInfoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FileTransferServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileTransferServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileTransferServiceStub>() {
        @java.lang.Override
        public FileTransferServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileTransferServiceStub(channel, callOptions);
        }
      };
    return FileTransferServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static FileTransferServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileTransferServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileTransferServiceBlockingV2Stub>() {
        @java.lang.Override
        public FileTransferServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileTransferServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return FileTransferServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FileTransferServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileTransferServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileTransferServiceBlockingStub>() {
        @java.lang.Override
        public FileTransferServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileTransferServiceBlockingStub(channel, callOptions);
        }
      };
    return FileTransferServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FileTransferServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileTransferServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileTransferServiceFutureStub>() {
        @java.lang.Override
        public FileTransferServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileTransferServiceFutureStub(channel, callOptions);
        }
      };
    return FileTransferServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * FileTransferService
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<com.example.combined.grpc.FileChunk> uploadFile(
        io.grpc.stub.StreamObserver<com.example.combined.grpc.UploadStatus> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getUploadFileMethod(), responseObserver);
    }

    /**
     */
    default void resolveConflict(com.example.combined.grpc.ConflictResolution request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.ResolutionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getResolveConflictMethod(), responseObserver);
    }

    /**
     */
    default void downloadFile(com.example.combined.grpc.FileRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.FileChunk> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDownloadFileMethod(), responseObserver);
    }

    /**
     */
    default void listFiles(com.example.combined.grpc.Empty request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.FileList> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListFilesMethod(), responseObserver);
    }

    /**
     */
    default void getFileInfo(com.example.combined.grpc.FileRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.FileInfo> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetFileInfoMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service FileTransferService.
   * <pre>
   * FileTransferService
   * </pre>
   */
  public static abstract class FileTransferServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return FileTransferServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service FileTransferService.
   * <pre>
   * FileTransferService
   * </pre>
   */
  public static final class FileTransferServiceStub
      extends io.grpc.stub.AbstractAsyncStub<FileTransferServiceStub> {
    private FileTransferServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileTransferServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileTransferServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.example.combined.grpc.FileChunk> uploadFile(
        io.grpc.stub.StreamObserver<com.example.combined.grpc.UploadStatus> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getUploadFileMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void resolveConflict(com.example.combined.grpc.ConflictResolution request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.ResolutionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getResolveConflictMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void downloadFile(com.example.combined.grpc.FileRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.FileChunk> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getDownloadFileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listFiles(com.example.combined.grpc.Empty request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.FileList> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListFilesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getFileInfo(com.example.combined.grpc.FileRequest request,
        io.grpc.stub.StreamObserver<com.example.combined.grpc.FileInfo> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetFileInfoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service FileTransferService.
   * <pre>
   * FileTransferService
   * </pre>
   */
  public static final class FileTransferServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<FileTransferServiceBlockingV2Stub> {
    private FileTransferServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileTransferServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileTransferServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<com.example.combined.grpc.FileChunk, com.example.combined.grpc.UploadStatus>
        uploadFile() {
      return io.grpc.stub.ClientCalls.blockingBidiStreamingCall(
          getChannel(), getUploadFileMethod(), getCallOptions());
    }

    /**
     */
    public com.example.combined.grpc.ResolutionResponse resolveConflict(com.example.combined.grpc.ConflictResolution request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getResolveConflictMethod(), getCallOptions(), request);
    }

    /**
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<?, com.example.combined.grpc.FileChunk>
        downloadFile(com.example.combined.grpc.FileRequest request) {
      return io.grpc.stub.ClientCalls.blockingV2ServerStreamingCall(
          getChannel(), getDownloadFileMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.FileList listFiles(com.example.combined.grpc.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListFilesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.FileInfo getFileInfo(com.example.combined.grpc.FileRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetFileInfoMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service FileTransferService.
   * <pre>
   * FileTransferService
   * </pre>
   */
  public static final class FileTransferServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<FileTransferServiceBlockingStub> {
    private FileTransferServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileTransferServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileTransferServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.combined.grpc.ResolutionResponse resolveConflict(com.example.combined.grpc.ConflictResolution request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getResolveConflictMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.example.combined.grpc.FileChunk> downloadFile(
        com.example.combined.grpc.FileRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getDownloadFileMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.FileList listFiles(com.example.combined.grpc.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListFilesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.combined.grpc.FileInfo getFileInfo(com.example.combined.grpc.FileRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetFileInfoMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service FileTransferService.
   * <pre>
   * FileTransferService
   * </pre>
   */
  public static final class FileTransferServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<FileTransferServiceFutureStub> {
    private FileTransferServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileTransferServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileTransferServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.ResolutionResponse> resolveConflict(
        com.example.combined.grpc.ConflictResolution request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getResolveConflictMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.FileList> listFiles(
        com.example.combined.grpc.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListFilesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.combined.grpc.FileInfo> getFileInfo(
        com.example.combined.grpc.FileRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetFileInfoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RESOLVE_CONFLICT = 0;
  private static final int METHODID_DOWNLOAD_FILE = 1;
  private static final int METHODID_LIST_FILES = 2;
  private static final int METHODID_GET_FILE_INFO = 3;
  private static final int METHODID_UPLOAD_FILE = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RESOLVE_CONFLICT:
          serviceImpl.resolveConflict((com.example.combined.grpc.ConflictResolution) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.ResolutionResponse>) responseObserver);
          break;
        case METHODID_DOWNLOAD_FILE:
          serviceImpl.downloadFile((com.example.combined.grpc.FileRequest) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.FileChunk>) responseObserver);
          break;
        case METHODID_LIST_FILES:
          serviceImpl.listFiles((com.example.combined.grpc.Empty) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.FileList>) responseObserver);
          break;
        case METHODID_GET_FILE_INFO:
          serviceImpl.getFileInfo((com.example.combined.grpc.FileRequest) request,
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.FileInfo>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPLOAD_FILE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.uploadFile(
              (io.grpc.stub.StreamObserver<com.example.combined.grpc.UploadStatus>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getUploadFileMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              com.example.combined.grpc.FileChunk,
              com.example.combined.grpc.UploadStatus>(
                service, METHODID_UPLOAD_FILE)))
        .addMethod(
          getResolveConflictMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.ConflictResolution,
              com.example.combined.grpc.ResolutionResponse>(
                service, METHODID_RESOLVE_CONFLICT)))
        .addMethod(
          getDownloadFileMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.example.combined.grpc.FileRequest,
              com.example.combined.grpc.FileChunk>(
                service, METHODID_DOWNLOAD_FILE)))
        .addMethod(
          getListFilesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.Empty,
              com.example.combined.grpc.FileList>(
                service, METHODID_LIST_FILES)))
        .addMethod(
          getGetFileInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.combined.grpc.FileRequest,
              com.example.combined.grpc.FileInfo>(
                service, METHODID_GET_FILE_INFO)))
        .build();
  }

  private static abstract class FileTransferServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FileTransferServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.combined.grpc.CombinedProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FileTransferService");
    }
  }

  private static final class FileTransferServiceFileDescriptorSupplier
      extends FileTransferServiceBaseDescriptorSupplier {
    FileTransferServiceFileDescriptorSupplier() {}
  }

  private static final class FileTransferServiceMethodDescriptorSupplier
      extends FileTransferServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    FileTransferServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FileTransferServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FileTransferServiceFileDescriptorSupplier())
              .addMethod(getUploadFileMethod())
              .addMethod(getResolveConflictMethod())
              .addMethod(getDownloadFileMethod())
              .addMethod(getListFilesMethod())
              .addMethod(getGetFileInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
