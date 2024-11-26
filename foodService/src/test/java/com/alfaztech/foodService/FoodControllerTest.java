package com.alfaztech.foodService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.alfaztech.foodService.controller.DishController;
import com.alfaztech.foodService.model.Dish;
import com.alfaztech.foodService.service.DishService;

@ExtendWith(MockitoExtension.class)
public class FoodControllerTest {
	
	@Mock
	private DishService dish;
	
	@InjectMocks
	private DishController dishController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		mockMvc=MockMvcBuilders.standaloneSetup(dishController).build();
	}
	
	@Test
	public void testGetAllDishes() throws Exception {
		List<Dish> mockDishs=new ArrayList<>();
		mockDishs.add(new Dish(1L, "Pasta1", "Delicious pasta with tomato sauce", new BigDecimal("12.99"), "Main Course", "http://example.com/pasta.jpg", null));
		mockDishs.add(new Dish(2L, "Pasta2", "Delicious pasta with tomato sauce", new BigDecimal("120.99"), "Main Course", "http://example.com/pasta.jpg", null));
		mockDishs.add(new Dish(3L, "Pasta3", "Delicious pasta with tomato sauce", new BigDecimal("1200.99"), "Main Course", "http://example.com/pasta.jpg", null));
		
		when(dish.getAllDishes()).thenReturn(mockDishs);
		
		mockMvc.perform(get("/api/dishes"))
		.andExpect(status().isOk());
	}

}
