package com.lyyang.test.testgrpc.server;

import com.lyyang.test.testgrpc.TestGrpcApplicationTests;
import com.lyyang.test.testgrpc.model.GreeterProto;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class GrpcServerServiceTest extends TestGrpcApplicationTests {

    @Autowired
    private GrpcServerService grpcServerService;

    @Test
    void authenticate() {

        GreeterProto.AuthRequest request = GreeterProto.AuthRequest.newBuilder().setPassword("AAA").setUsername("GGGG").build();
        StreamObserver<GreeterProto.AuthReply> responseObserver = Mockito.mock(StreamObserver.class);

        Assertions.assertThrows(ConstraintViolationException.class, ()-> grpcServerService.authenticate(request , responseObserver));
    }
}