package com.ms.user.repositories;

import com.ms.user.models.FoodEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodEntryRepository extends JpaRepository<FoodEntry, Long> {
    List<FoodEntry> findByDiaryId(Long diaryId);
}