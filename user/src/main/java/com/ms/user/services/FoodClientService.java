package com.ms.user.services;

import com.ms.user.models.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class FoodClientService {

    private final WebClient webClient;

    @Autowired
    public FoodClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082/api/foods").build();
    }

    public Food getFoodById(String foodId) {
        return webClient.get()
                .uri("/{id}", foodId)
                .retrieve()
                .bodyToMono(Food.class)
                .block();
    }
    public Flux<String> searchFoodsByName(String query) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(" /usda/search")
                        .queryParam("query", query)
                        .build())
                .retrieve()
                .bodyToFlux(String.class);
    }
}