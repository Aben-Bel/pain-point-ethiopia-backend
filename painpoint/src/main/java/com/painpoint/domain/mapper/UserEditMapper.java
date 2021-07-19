package com.painpoint.domain.mapper;

import java.util.Set;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.painpoint.domain.dto.CreateUserRequest;
import com.painpoint.domain.dto.UpdateUserRequest;
import com.painpoint.domain.model.Role;
import com.painpoint.domain.model.User;

@Mapper(componentModel = "spring")
public abstract class UserEditMapper {

    @Mapping(target = "authorities", ignore = true)
    public abstract User create(CreateUserRequest request);

    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "authorities", ignore = true)
    public abstract void update(UpdateUserRequest request, @MappingTarget User user);


    @AfterMapping
    protected void afterCreate(CreateUserRequest request, @MappingTarget User user) {
        if (request.getType() != null) {
            user.setAuthorities(Set.of(Role.of(request.getType()).orElseThrow()));
        }
    }
}