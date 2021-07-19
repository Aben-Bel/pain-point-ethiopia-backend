package com.painpoint.domain.mapper;


import java.util.Iterator;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.painpoint.domain.dto.UserView;
import com.painpoint.domain.model.Role;
import com.painpoint.domain.model.User;

@Mapper(componentModel = "spring")
public abstract class UserViewMapper {

    public abstract UserView toUserView(User user);

    public abstract List<UserView> toUserView(List<User> users);

    @AfterMapping
    protected void afterCreate(User user, @MappingTarget UserView userView) {
        Iterator<Role> iter = user.getAuthorities().iterator();
        if(iter.hasNext()) {
        	userView.setRole(iter.next().getAuthority());
        }
    }
}
