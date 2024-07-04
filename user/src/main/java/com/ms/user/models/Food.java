package com.ms.user.models;

import lombok.Data;

@Data
public class Food {
    private Long id;
    private String name;
    private int calories;
    private int protein;
    private int carbs;
    private int fats;
}