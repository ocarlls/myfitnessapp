package com.ms.food.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;


@Data
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int calories;
    private int protein;
    private int carbs;
    private int fats;
}