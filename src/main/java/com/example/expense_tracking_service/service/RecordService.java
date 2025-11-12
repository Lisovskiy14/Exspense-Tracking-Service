package com.example.expense_tracking_service.service;

import com.example.expense_tracking_service.dto.record.RecordRequestDto;
import com.example.expense_tracking_service.domain.Record;

import java.util.List;
import java.util.UUID;

public interface RecordService {
    List<Record> getFilteredRecords(UUID userId, UUID categoryId);
    Record getRecordById(UUID id);
    Record createRecord(RecordRequestDto recordRequestDto);
    void deleteRecordById(UUID id);
}
