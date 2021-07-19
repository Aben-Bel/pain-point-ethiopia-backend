package com.painpoint.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class Tag {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private String label;
}
