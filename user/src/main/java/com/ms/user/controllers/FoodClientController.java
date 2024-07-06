package com.ms.user.controllers;

import com.ms.user.services.FoodClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/foods")
public class FoodClientController {
    @Autowired
    FoodClientService foodClientService;
    @GetMapping("/search")
    public Flux<String> searchFoodUsda(@RequestParam String query){
        return foodClientService.searchFoodsByName(query);
    }
}
