package com.painpoint.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.painpoint.domain.dto.CreateUserRequest;
import com.painpoint.domain.dto.UpdateUserRequest;
import com.painpoint.domain.dto.UserView;
import com.painpoint.domain.mapper.UserEditMapper;
import com.painpoint.domain.mapper.UserViewMapper;
import com.painpoint.domain.model.User;
import com.painpoint.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserEditMapper userEditMapper;
	@Autowired
	private UserViewMapper userViewMapper;

	@Value("${jwt.token.prefix}")
	private static String PREFIX;
	@Value("${duplicate-username}")
	private String duplicateUsername;
	@Value("${password-mismatch}")
	private String passwordMismatch;

	@Override
	public UserView saveUser(CreateUserRequest request) {
		log.info("Create user " + request);
		Optional<User> duplicateUser = userRepository.findByUsername(request.getUsername());
		if (duplicateUser.isPresent()) {
			log.info("Duplicate user " + duplicateUser.toString());
			throw new ValidationException(duplicateUsername);
		}
		if (!Objects.equals(request.getPassword(), request.getRePassword())) {
			throw new ValidationException(passwordMismatch);
		}

		User user = userEditMapper.create(request);
		log.info(user.getAuthorities().toString());
		final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		user = userRepository.save(user);
		return userViewMapper.toUserView(user);
	}

	@Override
	public void logout(String token) {
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
						.orElseThrow(() -> new UsernameNotFoundException(String.format("User with username - %s, not found", username)));
	}

	@Override
	@Transactional
	public UserView deleteUser(Long userId) {
		User user = userRepository.getById(userId);
		userRepository.delete(user);
		return userViewMapper.toUserView(user);
	}

	@Transactional
	@Override
	public UserView updateUser(Long userId, UpdateUserRequest request) {
		User user = userRepository.getById(userId);
		userEditMapper.update(request, user);
		user = userRepository.save(user);
		return userViewMapper.toUserView(user);
	}
}
