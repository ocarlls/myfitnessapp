package com.ms.user.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Serving {
    @JsonProperty("serving_id")
    private String servingId;
    @JsonProperty("calories")
    private String calories;
    @JsonProperty("carbohydrate")
    private String carbohydrate;
    @JsonProperty("fat")
    private String fat;
    @JsonProperty("protein")
    private String protein;
    @JsonProperty("fiber")
    private String fiber;
    @JsonProperty("measurement_description")
    private String measurementDescription;
    @JsonProperty("metric_serving_amount")
    private String metricServingAmount;
    @JsonProperty("metric_serving_unit")
    private String metricServingUnit;
    @JsonProperty("number_of_units")
    private String numberOfUnits;
    @JsonProperty("saturated_fat")
    private String saturatedFat;
    @JsonProperty("serving_description")
    private String servingDescription;
}