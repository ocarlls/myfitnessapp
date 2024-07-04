package com.ms.food.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class FoodDataCentralClientService {

    @Value("${usda.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public FoodDataCentralClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.nal.usda.gov/fdc/v1").build();
    }

    public Mono<String> getFoodDetails(String fdcId) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/food/{fdcId}")
                        .queryParam("api_key", apiKey)
                        .build(fdcId))
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> searchFoodsByName(String query) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/foods/search")
                        .queryParam("query", query)
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}