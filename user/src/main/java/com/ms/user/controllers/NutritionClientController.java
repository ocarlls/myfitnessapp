package com.ms.user.controllers;
import com.ms.user.models.FoodResponse;
import com.ms.user.models.FoodSearchResponse;
import com.ms.user.services.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/food")
public class NutritionClientController {

    @Autowired
    private NutritionService nutritionService;

    @GetMapping("/{id}")
    public Mono<FoodResponse> getFoodDetailsById(@PathVariable String id) {
        return nutritionService.getFoodDetailsById(id);
    }
    @GetMapping()
    public Mono<FoodSearchResponse> searchFoodByName(@RequestParam String name) {
        return nutritionService.searchFoodsByName(name);
    }
}

