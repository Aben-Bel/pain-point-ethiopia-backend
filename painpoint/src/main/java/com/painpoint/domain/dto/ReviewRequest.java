package com.painpoint.domain.dto;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ReviewRequest {

	@NotBlank(message = "Comment is mandatory")
	@Size(max = 200)
	private String comment;
	@NotNull
	@PastOrPresent
	private LocalDate dateOfVisiting;
	@Min(0)
	@Max(5)
	private int star;
}
