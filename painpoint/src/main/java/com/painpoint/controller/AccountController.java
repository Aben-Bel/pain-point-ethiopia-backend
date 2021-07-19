package com.painpoint.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.painpoint.domain.dto.AuthRequest;
import com.painpoint.domain.dto.CreateUserRequest;
import com.painpoint.domain.dto.UserView;
import com.painpoint.domain.mapper.UserViewMapper;
import com.painpoint.domain.model.User;
import com.painpoint.service.UserService;
import com.painpoint.util.JwtTokenUtil;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserViewMapper userViewMapper;

	@PostMapping("/signup")
	public ResponseEntity<UserView> createUser(@Valid @RequestBody CreateUserRequest user) {
		UserView savedUser = userService.saveUser(user);
		return ResponseEntity.ok(savedUser);
	}

	@PostMapping("/login")
	public ResponseEntity<UserView> login(@RequestBody @Valid AuthRequest request) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		User user = (User) authenticate.getPrincipal();
		return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user)).body(userViewMapper.toUserView(user));

	}
}
