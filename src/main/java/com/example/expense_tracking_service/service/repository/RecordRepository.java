package com.example.expense_tracking_service.service.repository;

import org.springframework.stereotype.Repository;
import com.example.expense_tracking_service.domain.Record;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecordRepository {
    Record getRecordById(UUID id);
    void deleteRecordById(UUID id);
    Record saveRecord(Record record);
    List<Record> getFilteredRecords(UUID userId, UUID categoryId);
}
