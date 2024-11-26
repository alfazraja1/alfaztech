package com.alfaztech.foodService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alfaztech.foodService.model.Dish;
import com.alfaztech.foodService.repository.DishRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public Dish createDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish updateDish(Long id, Dish updatedDish) {
        Dish existingDish = dishRepository.findById(id).orElseThrow(() -> new RuntimeException("Dish not found"));
        existingDish.setName(updatedDish.getName());
        existingDish.setDescription(updatedDish.getDescription());
        existingDish.setPrice(updatedDish.getPrice());
        existingDish.setCategory(updatedDish.getCategory());
        existingDish.setImageUrl(updatedDish.getImageUrl());
        return dishRepository.save(existingDish);
    }

    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }

    public Optional<Dish> getDishById(Long id) {
        return dishRepository.findById(id);
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public List<Dish> getDishesByCategory(String category) {
        return dishRepository.findByCategory(category);
    }
}