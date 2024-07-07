package com.ms.user.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Diary {
    @Id
    @GeneratedValue
    private UUID id;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FoodEntry> foodEntries = new ArrayList<>();

    private float totalCalories;
    private float totalProtein;
    private float totalCarbs;
    private float totalFats;
}
