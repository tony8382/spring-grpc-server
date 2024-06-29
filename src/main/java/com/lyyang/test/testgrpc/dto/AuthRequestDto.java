package com.lyyang.test.testgrpc.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Size;

@Builder
@ToString
@Data
public class AuthRequestDto {
    @Size(max = 2, message = "Username 長度不得大於 2")
    private String username;
    private String password;
}
