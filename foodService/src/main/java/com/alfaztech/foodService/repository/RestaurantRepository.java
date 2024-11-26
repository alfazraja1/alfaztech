package com.alfaztech.foodService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alfaztech.foodService.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}