package com.example.expense_tracking_service.web.mapper;

import com.example.expense_tracking_service.dto.record.RecordDto;
import com.example.expense_tracking_service.domain.Record;
import com.example.expense_tracking_service.dto.record.RecordListDto;
import com.example.expense_tracking_service.dto.record.RecordRequest;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface RecordMapper {
    RecordDto toRecordDto(Record record);
    Record toRecord(RecordRequest recordRequest);
    RecordListDto toRecordListDto(Collection<Record> records);
}
