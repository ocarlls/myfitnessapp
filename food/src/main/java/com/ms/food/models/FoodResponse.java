package com.ms.food.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import java.util.List;
@Data
public class FoodResponse {
    private DataWrapper data;

    @Data
    public static class DataWrapper {
        @JsonAlias("getAllFood")
        private List<Food> foods;
    }
}
