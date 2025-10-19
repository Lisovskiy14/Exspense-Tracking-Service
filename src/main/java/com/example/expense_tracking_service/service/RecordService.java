package com.example.expense_tracking_service.service;

import com.example.expense_tracking_service.domain.Record;
import com.example.expense_tracking_service.service.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;

    public Record getRecordById(UUID recordId) {
        return recordRepository.getRecordById(recordId);
    }

    public void deleteRecordById(UUID recordId) {
        recordRepository.deleteRecordById(recordId);
    }

    public Record saveRecord(Record record) {
        record.setId(UUID.randomUUID());
        record.setDate(LocalDateTime.now());
        return recordRepository.saveRecord(record);
    }

    public List<Record> getFilteredRecords(UUID userId, UUID categoryId) {
        return recordRepository.getFilteredRecords(userId, categoryId);
    }
}
