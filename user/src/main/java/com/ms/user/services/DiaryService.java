package com.ms.user.services;

import com.ms.user.models.Diary;
//import com.ms.user.models.FoodResponse;
import com.ms.user.models.FoodEntry;
import com.ms.user.repositories.DiaryRepository;
import com.ms.user.repositories.FoodEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private FoodEntryRepository foodEntryRepository;

    public void addFoodEntry(String diaryId, FoodEntry foodEntry) {
        Diary diary = diaryRepository.findById(UUID.fromString(diaryId)).orElseThrow(() -> new RuntimeException("Diary not found"));
        foodEntry.setDiary(diary);
        foodEntryRepository.save(foodEntry);
        updateDiaryTotals(diary);
    }

    private void updateDiaryTotals(Diary diary) {
        int totalCalories = 0;
        int totalProtein = 0;
        int totalCarbs = 0;
        int totalFats = 0;

        for (FoodEntry entry : diary.getFoodEntries()) {

        }

        diary.setTotalCalories(totalCalories);
        diary.setTotalProtein(totalProtein);
        diary.setTotalCarbs(totalCarbs);
        diary.setTotalFats(totalFats);

        diaryRepository.save(diary);
    }

    public List<Diary> getDiariesByUserId(String userId) {
        return diaryRepository.findByUserId(UUID.fromString(userId));
    }
}
