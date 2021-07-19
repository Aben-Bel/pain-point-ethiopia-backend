package com.painpoint.domain.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReviewView {
	private Long id;
	private String comment;
	private LocalDate dateOfVisiting;
	private int star;
	private String reply;
	private String commentedBy;
}
