package com.painpoint.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class Problem {

	/**
	 * Problem Posts Has owner It has a title and body It has votes
	 * tags(categories) Comments
	 * 
	 */

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private String title;

	@NonNull
	private String body;

	private int vote;

	@OneToMany
	private List<Tag> categories;

}
