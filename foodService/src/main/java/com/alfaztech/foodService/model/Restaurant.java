package com.alfaztech.foodService.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Restaurant {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String location;
	    private Double rating;
	    private String categories;

	    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	    @JsonManagedReference
	    private List<Dish> dishes;

	    // Getters and Setters
	    

	    public Long getId() {
	        return id;
	    }

	    public Restaurant() {
			
			// TODO Auto-generated constructor stub
		}

		public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getLocation() {
	        return location;
	    }

	    public void setLocation(String location) {
	        this.location = location;
	    }

	    public Double getRating() {
	        return rating;
	    }

	    public void setRating(Double rating) {
	        this.rating = rating;
	    }

	    public String getCategories() {
	        return categories;
	    }

	    public void setCategories(String categories) {
	        this.categories = categories;
	    }


	    public List<Dish> getDishes() {
	        return dishes;
	    }

	    public void setDishes(List<Dish> dishes) {
	        this.dishes = dishes;
	    }
	}
