package com.ms.user.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Food {
    @JsonProperty("food_id")
    private String foodId;
    @JsonProperty("food_name")
    private String foodName;
    @JsonProperty("food_description")
    private String foodDescription;
    @JsonProperty("food_type")
    private String foodType;
    @JsonProperty("food_url")
    private String foodUrl;
    @JsonProperty("servings")
    private Servings servings;
}