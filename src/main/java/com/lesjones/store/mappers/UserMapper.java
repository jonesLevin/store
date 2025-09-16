package com.lesjones.store.mappers;

import com.lesjones.store.dtos.RegisterUserRequest;
import com.lesjones.store.dtos.UserDto;
import com.lesjones.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
}
