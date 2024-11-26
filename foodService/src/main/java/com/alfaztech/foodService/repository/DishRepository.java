package com.alfaztech.foodService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alfaztech.foodService.model.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {
    // You can add custom queries if needed, e.g., find by category
    List<Dish> findByCategory(String category);
}