package com.painpoint.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.painpoint.domain.dto.ListResponse;
import com.painpoint.domain.dto.UpdateUserRequest;
import com.painpoint.domain.dto.UserView;
import com.painpoint.domain.mapper.UserViewMapper;
import com.painpoint.domain.model.User;
import com.painpoint.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserViewMapper userViewMapper;

	@GetMapping
	public ListResponse<UserView> getUsers() {
		List<User> users = userService.getUsers();
		return new ListResponse<>(userViewMapper.toUserView(users));
	}

	@PutMapping("{userId}")
	public UserView updateUser(@PathVariable Long userId, @RequestBody @Valid UpdateUserRequest request) {
		return userService.updateUser(userId, request);
	}

	@DeleteMapping("{userId}")
	public UserView deleteUser(@PathVariable Long userId) {
		return userService.deleteUser(userId);
	}
}
