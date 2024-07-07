package com.ms.user.services;
import com.ms.user.models.Diary;
import com.ms.user.models.DiaryCreationLog;
import com.ms.user.models.User;
import com.ms.user.repositories.DiaryCreationLogRepository;
import com.ms.user.repositories.DiaryRepository;
import com.ms.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Service
public class DiaryCreationService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiaryCreationLogRepository diaryCreationLogRepository;

    @PostConstruct
    public void createDiariesForAllUsers() {
        DiaryCreationLog log = diaryCreationLogRepository.findAll().stream().findFirst().orElse(new DiaryCreationLog());
        LocalDate lastCreatedDate = log.getLastCreatedDate();
        LocalDate today = LocalDate.now();

        if (lastCreatedDate == null || !lastCreatedDate.equals(today)) {
            List<User> users = userRepository.findAll();
            for (User user : users) {
                Diary diary = new Diary();
                diary.setUser(user);
                diary.setDate(today);
                diaryRepository.save(diary);
            }
            log.setLastCreatedDate(today);
            diaryCreationLogRepository.save(log);
        }
    }
}

