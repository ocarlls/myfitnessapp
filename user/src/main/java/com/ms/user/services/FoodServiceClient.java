package com.ms.user.services;

import com.ms.user.models.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FoodServiceClient {

    private final WebClient webClient;

    @Autowired
    public FoodServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082/api").build();
    }

    public Food getFoodById(Long foodId) {
        return webClient.get()
                .uri("/{id}", foodId)
                .retrieve()
                .bodyToMono(Food.class)
                .block();
    }
}