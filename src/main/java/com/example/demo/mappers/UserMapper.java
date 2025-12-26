package com.example.demo.mappers;

import com.example.demo.dtos.UserCreateDto;
import com.example.demo.dtos.UserResponseDto;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserCreateDto dto);

    @Mapping(target = "roles", source = "roles")
    UserResponseDto toDto(User user);

    default List<String> mapRoles(List<Role> roles) {
        if (roles == null) return null;
        return roles.stream().map(Role::getName).toList();
    }
}