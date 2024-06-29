package com.lyyang.test.testgrpc.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class AuthRequestDto {
    private String username;
    private String password;
}
