package com.painpoint.domain.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RestaurantDetailView {

	private Long id;	
	private String name;
	private List<ReviewView> reviews = new ArrayList<>();
	private double rating;
	private int highestRating;
	private int lowestRating;
}
