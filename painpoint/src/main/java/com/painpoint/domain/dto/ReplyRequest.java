package com.painpoint.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ReplyRequest {

	@NotBlank
	@Size(max = 200)
	private String reply;
}
