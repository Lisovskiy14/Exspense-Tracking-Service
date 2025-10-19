package com.example.expense_tracking_service.web;

import com.example.expense_tracking_service.dto.record.RecordRequest;
import com.example.expense_tracking_service.service.RecordService;
import com.example.expense_tracking_service.web.exception.NoRequestParamsException;
import com.example.expense_tracking_service.web.mapper.RecordMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/records")
public class RecordController {
    private final RecordService recordService;
    private final RecordMapper recordMapper;

    @GetMapping("/{recordId}")
    public ResponseEntity<Object> getRecordById(@PathVariable UUID recordId) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(recordMapper.toRecordDto(
                        recordService.getRecordById(recordId)));
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<Object> deleteRecordById(@PathVariable UUID recordId) {
        recordService.deleteRecordById(recordId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> saveRecord(@RequestBody @Valid RecordRequest recordRequest) {
        return ResponseEntity.status(201)
                .contentType(MediaType.APPLICATION_JSON)
                .body(recordMapper.toRecordDto(
                        recordService.saveRecord(
                                recordMapper.toRecord(recordRequest))));
    }

    @GetMapping
    public ResponseEntity<Object> getFilteredRecords(
            @RequestParam(value = "userId", required = false) UUID userId,
            @RequestParam(value = "categoryId", required = false) UUID categoryId
    ) {
        if (userId == null && categoryId == null) {
            throw new NoRequestParamsException();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(recordMapper.toRecordListDto(
                        recordService.getFilteredRecords(userId, categoryId)));
    }
}
