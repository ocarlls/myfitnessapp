package com.ms.user.controllers;

import com.ms.user.services.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @PostMapping("/addFood")
    public void addFoodEntry(@RequestParam UUID diaryId, @RequestParam String foodId, @RequestParam float quantity) {
        diaryService.addFoodEntry(diaryId, foodId, quantity);
    }
}