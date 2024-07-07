package com.ms.user.services;

import com.ms.user.models.Diary;
//import com.ms.user.models.FoodResponse;
import com.ms.user.models.FoodEntry;
import com.ms.user.models.Serving;
import com.ms.user.repositories.DiaryRepository;
import com.ms.user.repositories.FoodEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private FoodEntryRepository foodEntryRepository;

    @Autowired
    private NutritionService nutritionService;

    public void addFoodEntry(UUID diaryId, String foodId, float quantity) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new RuntimeException("Diary not found"));

        nutritionService.getFoodDetailsById(foodId).subscribe(food -> {
            List<Serving> servingList = food.getFood().getServings().getServing();
            Optional<Serving> optionalServing = servingList.stream()
                    .filter(s -> s.getMetricServingAmount().equals("100.000"))
                    .findFirst();
            Serving serving = optionalServing.orElseGet(() -> servingList.get(0));
            float calories = (Float.parseFloat(serving.getCalories())/Float.parseFloat(serving.getMetricServingAmount()))* quantity;
            float protein = (Float.parseFloat(serving.getProtein())/Float.parseFloat(serving.getMetricServingAmount())) * quantity;
            float carbs = (Float.parseFloat(serving.getCarbohydrate())/Float.parseFloat(serving.getMetricServingAmount())) * quantity;
            float fats = (Float.parseFloat(serving.getFat())/Float.parseFloat(serving.getMetricServingAmount())) * quantity;

            FoodEntry foodEntry = new FoodEntry();
            foodEntry.setFoodId(foodId);
            foodEntry.setFoodName(food.getFood().getFoodName());
            foodEntry.setDiary(diary);
            foodEntry.setQuantity(quantity);
            foodEntry.setCalories(calories);
            foodEntry.setProtein(protein);
            foodEntry.setCarbs(carbs);
            foodEntry.setFats(fats);

            foodEntryRepository.save(foodEntry);
            updateDiaryTotals(diary);
        });
    }

    private void updateDiaryTotals(Diary diary) {
        float totalCalories = 0;
        float totalProtein = 0;
        float totalCarbs = 0;
        float totalFats = 0;
        List<FoodEntry> foodEntries = foodEntryRepository.findByDiaryId(diary.getId());
        for (FoodEntry entry : foodEntries) {
            totalCalories += entry.getCalories();
            totalProtein += entry.getProtein();
            totalCarbs += entry.getCarbs();
            totalFats += entry.getFats();
        }

        diary.setTotalCalories(totalCalories);
        diary.setTotalProtein(totalProtein);
        diary.setTotalCarbs(totalCarbs);
        diary.setTotalFats(totalFats);

        diaryRepository.save(diary);
    }

    public List<Diary> getDiariesByUser(UUID userId) {
        return diaryRepository.findByUserId(userId);
    }
}
