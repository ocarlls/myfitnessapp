package com.ms.user.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Food {
    private UUID id;
    private String name;
    private int calories;
    private int protein;
    private int carbs;
    private int fats;
}