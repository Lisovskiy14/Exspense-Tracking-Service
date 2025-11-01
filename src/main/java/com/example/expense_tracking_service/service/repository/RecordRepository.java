package com.example.expense_tracking_service.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.expense_tracking_service.domain.Record;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecordRepository extends JpaRepository<Record, UUID> {
    List<Record> findRecordByUserIdOrCategoryId(UUID userId, UUID categoryId);
}
