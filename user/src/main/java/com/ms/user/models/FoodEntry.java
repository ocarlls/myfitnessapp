package com.ms.user.models;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class FoodEntry {
    @Id@GeneratedValue
    private UUID id;

    private Long foodId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    private int quantity;
    private int calories;
    private int protein;
    private int carbs;
    private int fats;
}