/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  io.grpc.Metadata
 *  io.grpc.Metadata$AsciiMarshaller
 *  io.grpc.Metadata$Key
 *  io.grpc.ServerCall
 *  io.grpc.ServerCall$Listener
 *  io.grpc.ServerCallHandler
 *  io.grpc.ServerInterceptor
 */
package Main;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

public class ServerPortInterceptor
implements ServerInterceptor {
    private final int serverPort;

    public ServerPortInterceptor(int serverPort) {
        this.serverPort = serverPort;
    }

    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of((String)"server-port", (Metadata.AsciiMarshaller)Metadata.ASCII_STRING_MARSHALLER), (Object)String.valueOf(this.serverPort));
        call.sendHeaders(metadata);
        return next.startCall(call, headers);
    }
}

