package com.ms.user.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.user.models.FoodResponse;
import com.ms.user.models.FoodSearchResponse;
import com.ms.user.models.Serving;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class NutritionService {

    private final OAuthClientService oAuthService;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public NutritionService(OAuthClientService oAuthService, ObjectMapper objectMapper) {
        this.oAuthService = oAuthService;
        this.webClient = WebClient.builder().build();
        this.objectMapper = objectMapper;
    }

    public Mono<FoodResponse> getFoodDetailsById(String foodId) {
        return oAuthService.getAccessToken()
                .flatMap(token ->
                        webClient.get()
                                .uri("https://platform.fatsecret.com/rest/server.api?method=food.get.v2&food_id=" + foodId + "&format=json")
                                .headers(headers -> headers.setBearerAuth(token))
                                .retrieve()
                                .bodyToMono(String.class)
                )
                .flatMap(response -> {
                    try {
                        FoodResponse foodResponse = objectMapper.readValue(response, FoodResponse.class);
                        Serving servingGrams = foodResponse.getFood().getServings().getServing().stream()
                                .filter(serving -> "g".equals(serving.getMeasurementDescription()))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("Serving not found"));

                        foodResponse.getFood().getServings().setServing(List.of(servingGrams));
                        return Mono.just(foodResponse);
                    } catch (Exception e) {
                        return Mono.error(new RuntimeException("Erro ao desserializar a resposta", e));
                    }
                });
    }

    public Mono<FoodSearchResponse> searchFoodsByName(String query) {
        return oAuthService.getAccessToken()
                .flatMap(token ->
                        webClient.get()
                                .uri("https://platform.fatsecret.com/rest/server.api?method=foods.search&search_expression=" + query + "&format=json")
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
