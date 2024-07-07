package com.ms.user.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.user.models.FoodResponse;
import com.ms.user.models.FoodSearchResponse;
import com.ms.user.models.Serving;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NutritionService {

    private final OAuthClientService oAuthService;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public NutritionService(OAuthClientService oAuthService, ObjectMapper objectMapper) {
        this.oAuthService = oAuthService;
        this.webClient = WebClient.builder()
                .baseUrl("https://platform.fatsecret.com/rest/server.api")
                .build();
        this.objectMapper = objectMapper;
    }

    public Mono<FoodResponse> getFoodDetailsById(String foodId) {
        return oAuthService.getAccessToken()
                .flatMap(token ->
                        webClient.get()
                                .uri("?method=food.get.v2&food_id=" + foodId + "&format=json")
                                .headers(headers -> headers.setBearerAuth(token))
                                .retrieve()
                                .bodyToMono(String.class)
                )
                .flatMap(response -> {
                    try {
                        FoodResponse foodResponse = objectMapper.readValue(response, FoodResponse.class);
                        List<Serving> servingGrams = foodResponse.getFood().getServings().getServing().stream()
                                .filter(serving -> "g".equals(serving.getMetricServingUnit()))
                                .collect(Collectors.toList());
                        foodResponse.getFood().getServings().setServing(servingGrams);
                        return Mono.just(foodResponse);
                    } catch (RuntimeException | JsonProcessingException e) {
                        return Mono.error(e);
                    }
                });
    }

    public Mono<FoodSearchResponse> searchFoodsByName(String query) {
        return oAuthService.getAccessToken()
                .flatMap(token ->
                        webClient.get()
                                .uri("?method=foods.search&search_expression=" + query + "&format=json")
                                .headers(headers -> headers.setBearerAuth(token))
                                .retrieve()
                                .bodyToMono(String.class)
                )
                .flatMap(response -> {
                    try {
                        FoodSearchResponse foodSearchResponse = objectMapper.readValue(response, FoodSearchResponse.class);
                        return Mono.just(foodSearchResponse);
                    } catch (Exception e) {
                        return Mono.error(new RuntimeException("Erro ao desserializar a resposta", e));
                    }
                });
    }
}
