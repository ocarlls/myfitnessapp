package com.ms.user.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Servings {
    @JsonProperty("serving")
    private List<Serving> serving;
}