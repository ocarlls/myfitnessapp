package com.ms.food.controllers;

import com.ms.food.models.Food;
import com.ms.food.services.FoodDataCentralClientService;
import com.ms.food.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private FoodDataCentralClientService foodDataCentralClientService;

    @GetMapping("/api/food/{fdcId}")
    public Mono<String> getFoodDetails(@PathVariable String fdcId) {
        return foodDataCentralClientService.getFoodDetails(fdcId);
    }

    @GetMapping("/api/foods/search")
    public Mono<String> searchFoodsByName(@RequestParam String query) {
        return foodDataCentralClientService.searchFoodsByName(query);
    }

    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{id}")
    public Food getFoodById(@PathVariable Long id) {
        return foodService.getFoodById(id);
    }

    @PostMapping
    public Food saveFood(@RequestBody Food food) {
        return foodService.saveFood(food);
    }

    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
    }
}