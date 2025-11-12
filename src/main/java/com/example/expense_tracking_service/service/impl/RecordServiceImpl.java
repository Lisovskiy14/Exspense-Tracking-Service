package com.example.expense_tracking_service.service.impl;

import com.example.expense_tracking_service.domain.Category;
import com.example.expense_tracking_service.domain.Record;
import com.example.expense_tracking_service.domain.User;
import com.example.expense_tracking_service.dto.record.RecordRequestDto;
import com.example.expense_tracking_service.service.RecordService;
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
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final AccountServiceImpl accountServiceImpl;

    @Override
    public List<Record> getFilteredRecords(UUID userId, UUID categoryId) {
        if (userId == null && categoryId == null) {
            return recordRepository.findAll();
        }
        return recordRepository.findRecordByUserIdOrCategoryId(userId, categoryId);
    }

    @Override
    public Record getRecordById(UUID recordId) {
        Optional<Record> record = recordRepository.findById(recordId);
        if (record.isEmpty()) {
            throw new RecordNotFoundException(recordId.toString());
        }
        return record.get();
    }

    @Override
    public Record createRecord(RecordRequestDto recordRequestDto) {
        User userProxy = userRepository.getReferenceById(
                UUID.fromString(recordRequestDto.getUserId()));
        Category categoryProxy = categoryRepository.getReferenceById(
                UUID.fromString(recordRequestDto.getCategoryId()));

        Record record = Record.builder()
                .user(userProxy)
                .category(categoryProxy)
                .date(LocalDateTime.now())
                .costAmount(recordRequestDto.getCostAmount())
                .build();

        accountServiceImpl.registerAnExpense(userProxy.getId(), record.getCostAmount());

        return recordRepository.save(record);
    }

    @Override
    public void deleteRecordById(UUID recordId) {
        recordRepository.deleteById(recordId);
    }
}
