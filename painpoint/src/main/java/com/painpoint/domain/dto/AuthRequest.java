package com.painpoint.domain.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AuthRequest {

	@NotBlank(message="username is mandatory")
    private String username;
	@NotBlank(message="password is mandatory")
    private String password;

}
