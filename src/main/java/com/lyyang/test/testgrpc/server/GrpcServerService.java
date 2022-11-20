package com.lyyang.test.testgrpc.server;

import com.lyyang.test.testgrpc.model.GreeterGrpc;
import com.lyyang.test.testgrpc.model.GreeterProto;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.security.access.annotation.Secured;

@GrpcService
public class GrpcServerService extends GreeterGrpc.GreeterImplBase {
    @Override
    @Secured("ROLE_GREET")
    public void sayHello(GreeterProto.HelloRequest request, StreamObserver<GreeterProto.HelloReply> responseObserver) {

        GreeterProto.HelloReply reply = GreeterProto.HelloReply.newBuilder().setMessage("Hello ==> " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
