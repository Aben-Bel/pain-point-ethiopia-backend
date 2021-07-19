package com.painpoint.domain.dto;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class UpdateReviewRequest {

	private String comment;
	private LocalDate dateOfVisiting;
	@Min(0) @Max(5)
	private Integer star;
	private String reply;
}
