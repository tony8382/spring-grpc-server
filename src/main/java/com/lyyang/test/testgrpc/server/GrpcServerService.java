package com.lyyang.test.testgrpc.server;

import com.lyyang.test.testgrpc.dto.AuthRequestDto;
import com.lyyang.test.testgrpc.dto.AuthRequestMapper;
import com.lyyang.test.testgrpc.jwt.JwtTokenProvider;
import com.lyyang.test.testgrpc.model.GreeterGrpc;
import com.lyyang.test.testgrpc.model.GreeterProto;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.Validator;

@Slf4j
@GrpcService
@Secured("ROLE_USER")
@Validated
public class GrpcServerService extends GreeterGrpc.GreeterImplBase {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private Validator validator;

    @Override
    public void sayHello(GreeterProto.HelloRequest request, StreamObserver<GreeterProto.HelloReply> responseObserver) {

        GreeterProto.HelloReply reply = GreeterProto.HelloReply.newBuilder().setMessage("Hello User ==> " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void sayHelloAdmin(GreeterProto.HelloRequest request, StreamObserver<GreeterProto.HelloReply> responseObserver) {
        GreeterProto.HelloReply reply = GreeterProto.HelloReply.newBuilder().setMessage("Hello Admin ==> " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void authenticate(GreeterProto.AuthRequest request, StreamObserver<GreeterProto.AuthReply> responseObserver) {
        AuthRequestDto dto = AuthRequestMapper.INSTANCE.toDTO(request);
        String token = authenticateE(dto);

        GreeterProto.AuthReply reply = GreeterProto.AuthReply.newBuilder()
                .setToken(token)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();

    }

    String authenticateE(@Valid AuthRequestDto dto) {

        //Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        String token = "G";
        return token;
    }
}
