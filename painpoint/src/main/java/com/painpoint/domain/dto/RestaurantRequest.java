package com.painpoint.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RestaurantRequest {

	@NotBlank
	@Size(max = 20)
	private String name;
}
