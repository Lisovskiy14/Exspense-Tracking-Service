package com.example.expense_tracking_service.service.repository.impl;

import com.example.expense_tracking_service.service.exception.RecordNotFoundException;
import com.example.expense_tracking_service.service.repository.RecordRepository;
import com.example.expense_tracking_service.domain.Record;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class RecordRepositoryImpl implements RecordRepository {
    private final Map<UUID, Record> records = new HashMap<>();

    @Override
    public Record getRecordById(UUID id) {
        if (!records.containsKey(id)) {
            throw new RecordNotFoundException(id.toString());
        }
        return records.get(id);
    }

    @Override
    public void deleteRecordById(UUID id) {
        records.remove(id);
    }

    @Override
    public Record saveRecord(Record record) {
        records.put(record.getId(), record);
        return record;
    }

    @Override
    public List<Record> getFilteredRecords(UUID userId, UUID categoryId) {
        if (userId != null && categoryId != null) {
            return records.values().stream()
                    .filter(record -> userId.equals(record.getUserId()))
                    .filter(record -> categoryId.equals(record.getCategoryId()))
                    .toList();
        } else if (userId != null) {
            return records.values().stream()
                    .filter(record -> userId.equals(record.getUserId()))
                    .toList();
        } else {
            return records.values().stream()
                    .filter(record -> categoryId.equals(record.getCategoryId()))
                    .toList();
        }
    }
}
