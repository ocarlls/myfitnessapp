package com.ms.food.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.food.models.Food;
import com.ms.food.models.FoodResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ClientGraphQLService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public ClientGraphQLService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:4000")
                .build();
    }

    public Flux<Food> fetchDataFromGraphQL() {
        return webClient.post()
                .uri("/graphql")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{ \"query\": \"query MyQuery { getAllFood { name nutrients { kcal carbohydrates protein lipids } } }\" }")
                .retrieve()
                .bodyToMono(String.class)
                .flatMapMany(response -> {
                    try {
                        FoodResponse foodResponse = objectMapper.readValue(response, FoodResponse.class);
                        return Flux.fromIterable(foodResponse.getData().getFoods());
                    } catch (Exception e) {
                        return Flux.error(new IllegalArgumentException("Erro ao desserializar resposta JSON", e));
                    }
                });
    }

}