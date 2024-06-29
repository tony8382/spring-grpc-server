package com.lyyang.test.testgrpc.dto;

import com.lyyang.test.testgrpc.model.GreeterProto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class AuthRequestMapperTest {

    @Test
    void toDTO() {
        GreeterProto.AuthRequest request = GreeterProto.AuthRequest.newBuilder().setPassword("AAA").setUsername("GGGG").build();
        AuthRequestDto dto = AuthRequestMapper.INSTANCE.toDTO(request);
        log.info("request: {}", request);
        log.info("dto: {}", dto);

    }

    @Test
    void toVO() {

        AuthRequestDto dto = AuthRequestDto.builder().password("DDD").username("GGG").build();
        GreeterProto.AuthRequest request = AuthRequestMapper.INSTANCE.toVO(dto);
        log.info("request: {}", request);
        log.info("dto: {}", dto);
    }
}