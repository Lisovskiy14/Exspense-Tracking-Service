package com.example.expense_tracking_service.service;

import com.example.expense_tracking_service.domain.Category;
import com.example.expense_tracking_service.domain.Record;
import com.example.expense_tracking_service.domain.User;
import com.example.expense_tracking_service.dto.record.RecordRequest;
import com.example.expense_tracking_service.service.exception.RecordNotFoundException;
import com.example.expense_tracking_service.service.repository.CategoryRepository;
import com.example.expense_tracking_service.service.repository.RecordRepository;
import com.example.expense_tracking_service.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public Record getRecordById(UUID recordId) {
        Optional<Record> record = recordRepository.findById(recordId);
        if (record.isEmpty()) {
            throw new RecordNotFoundException(recordId.toString());
        }
        return record.get();
    }

    public void deleteRecordById(UUID recordId) {
        recordRepository.deleteById(recordId);
    }

    public Record saveRecord(RecordRequest recordRequest) {
        User userProxy = userRepository.getReferenceById(
                UUID.fromString(recordRequest.getUserId()));
        Category categoryProxy = categoryRepository.getReferenceById(
                UUID.fromString(recordRequest.getCategoryId()));

        Record record = Record.builder()
                .user(userProxy)
                .category(categoryProxy)
                .date(LocalDateTime.now())
                .costAmount(recordRequest.getCostAmount())
                .build();

        return recordRepository.save(record);
    }

    public List<Record> getFilteredRecords(UUID userId, UUID categoryId) {
        return recordRepository.findRecordByUserIdOrCategoryId(userId, categoryId);
    }
}
