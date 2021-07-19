package com.painpoint.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateUserRequest {

	@NotBlank
	@Size(max = 20)
	private String username;
	@NotBlank
	@Size(max = 20)
	private String fullName;
	@NotBlank
	@Size(min = 6, max = 20)
	private String password;
	@NotBlank
	@Size(min = 6, max = 20)
	private String rePassword;
	@Pattern(regexp = "REGULAR_USER|RESTAURANT_USER|ADMIN_USER")
	private String type;

}