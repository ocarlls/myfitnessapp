package com.ms.user.repositories;

import com.ms.user.models.FoodEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FoodEntryRepository extends JpaRepository<FoodEntry, UUID> {
    List<FoodEntry> findByDiaryId(UUID diaryId);
}