package com.painpoint.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.painpoint.domain.dto.CreateUserRequest;
import com.painpoint.domain.dto.UpdateUserRequest;
import com.painpoint.domain.dto.UserView;
import com.painpoint.domain.model.User;

public interface UserService extends UserDetailsService {

	UserView saveUser(CreateUserRequest user);

	void logout(String token);

	List<User> getUsers();

	UserView updateUser(Long userId, UpdateUserRequest request);

	UserView deleteUser(Long userId);

}