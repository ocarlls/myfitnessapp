package com.ms.user.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Serving {
    @JsonProperty("calcium")
    private String calcium;
    @JsonProperty("calories")
    private String calories;
    @JsonProperty("carbohydrate")
    private String carbohydrate;
    @JsonProperty("cholesterol")
    private String cholesterol;
    @JsonProperty("fat")
    private String fat;
    @JsonProperty("fiber")
    private String fiber;
    @JsonProperty("iron")
    private String iron;
    @JsonProperty("measurement_description")
    private String measurementDescription;
    @JsonProperty("metric_serving_amount")
    private String metricServingAmount;
    @JsonProperty("metric_serving_unit")
    private String metricServingUnit;
    @JsonProperty("monounsaturated_fat")
    private String monounsaturatedFat;
    @JsonProperty("number_of_units")
    private String numberOfUnits;
    @JsonProperty("polyunsaturated_fat")
    private String polyunsaturatedFat;
    @JsonProperty("potassium")
    private String potassium;
    @JsonProperty("protein")
    private String protein;
    @JsonProperty("saturated_fat")
    private String saturatedFat;
    @JsonProperty("serving_description")
    private String servingDescription;
    @JsonProperty("sodium")
    private String sodium;
    @JsonProperty("sugar")
    private String sugar;
    @JsonProperty("vitamin_a")
    private String vitaminA;
    @JsonProperty("vitamin_c")
    private String vitaminC;
}