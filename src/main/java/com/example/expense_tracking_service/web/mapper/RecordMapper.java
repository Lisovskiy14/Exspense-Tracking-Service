package com.example.expense_tracking_service.web.mapper;

import com.example.expense_tracking_service.dto.record.RecordDto;
import com.example.expense_tracking_service.domain.Record;
import com.example.expense_tracking_service.dto.record.RecordRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface RecordMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "category.id", target = "categoryId")
    RecordDto toRecordDto(Record record);
    Record toRecord(RecordRequest recordRequest);
}
