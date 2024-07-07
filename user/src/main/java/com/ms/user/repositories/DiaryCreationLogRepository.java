package com.ms.user.repositories;
import com.ms.user.models.DiaryCreationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryCreationLogRepository extends JpaRepository<DiaryCreationLog, Long> {
}

