package com.lyyang.test.testgrpc.dto;

import com.lyyang.test.testgrpc.model.GreeterProto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRequestMapper {
    AuthRequestMapper INSTANCE = Mappers.getMapper(AuthRequestMapper.class);

    AuthRequestDto toDTO(GreeterProto.AuthRequest authRequest);

    GreeterProto.AuthRequest toVO(AuthRequestDto authRequestDto);
}