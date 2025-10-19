package com.example.expense_tracking_service.web.mapper;

import com.example.expense_tracking_service.domain.User;
import com.example.expense_tracking_service.dto.user.UserDto;
import com.example.expense_tracking_service.dto.user.UserRequest;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    User toUser(UserRequest userRequest);
}
