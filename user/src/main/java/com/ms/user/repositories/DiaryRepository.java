package com.ms.user.repositories;

import com.ms.user.models.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUserId(UUID userId);
}