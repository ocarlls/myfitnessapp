package com.ms.user.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class OAuthClientService {

    @Value("${fatsecret.client-id}")
    private String clientId;

    @Value("${fatsecret.client-secret}")
    private String clientSecret;

    @Value("${fatsecret.token-uri}")
    private String tokenUri;

    private final WebClient webClient = WebClient.builder().build();

    public Mono<String> getAccessToken() {
        return webClient.post()
                .uri(tokenUri)
                .headers(headers -> {
                    headers.setBasicAuth(clientId, clientSecret);
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                })
                .body(BodyInserters.fromFormData("grant_type", "client_credentials")
                        .with("scope", "basic")) // Adicionando o parâmetro scope
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (String) response.get("access_token"));
    }
}
