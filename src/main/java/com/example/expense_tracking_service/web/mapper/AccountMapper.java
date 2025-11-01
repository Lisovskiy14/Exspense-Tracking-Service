package com.example.expense_tracking_service.web.mapper;

import com.example.expense_tracking_service.domain.Account;
import com.example.expense_tracking_service.dto.account.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

//    @Mapping(source = "user.id", target = "userId")
    AccountDto toAccountDto(Account account);
}
