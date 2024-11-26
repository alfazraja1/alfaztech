package com.alfaztech.foodService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alfaztech.foodService.model.Restaurant;
import com.alfaztech.foodService.repository.RestaurantRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long id, Restaurant updatedRestaurant) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        existingRestaurant.setName(updatedRestaurant.getName());
        existingRestaurant.setLocation(updatedRestaurant.getLocation());
        existingRestaurant.setRating(updatedRestaurant.getRating());
        existingRestaurant.setCategories(updatedRestaurant.getCategories());
        return restaurantRepository.save(existingRestaurant);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
}