package com.lesjones.store.mappers;

import com.lesjones.store.dtos.RegisterUserRequest;
import com.lesjones.store.dtos.UpdateUserRequest;
import com.lesjones.store.dtos.UserDto;
import com.lesjones.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
