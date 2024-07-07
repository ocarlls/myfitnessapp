package com.ms.user.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class FoodEntry {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryId;
    private String foodId;
    private String foodName;
    private float quantity;
    private float calories;
    private float protein;
    private float carbs;
    private float fats;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;
}
