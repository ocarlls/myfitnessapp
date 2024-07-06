package com.ms.food.controllers;

import com.ms.food.models.Food;
import com.ms.food.services.ClientGraphQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/graph")
public class FoodGraphQLController {

    private final ClientGraphQLService clientGraphQLService;

    @Autowired
    public FoodGraphQLController(ClientGraphQLService clientGraphQLService) {
        this.clientGraphQLService = clientGraphQLService;
    }

    @GetMapping("/getAllFood")
    public Flux<Food> getAllFood() {
        return clientGraphQLService.fetchDataFromGraphQL();
    }
}
