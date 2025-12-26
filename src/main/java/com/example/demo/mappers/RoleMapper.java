package com.example.demo.mappers;

import com.example.demo.dtos.RoleCreateDto;
import com.example.demo.dtos.RoleResponseDto;
import com.example.demo.models.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponseDto toDto(Role role);

    @Mapping(target = "id", ignore = true)
    Role toEntity(RoleCreateDto dto);
}