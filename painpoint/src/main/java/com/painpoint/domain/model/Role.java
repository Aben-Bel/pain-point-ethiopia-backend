package com.painpoint.domain.model;

import java.util.Objects;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Role implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String REGULAR_USER = "ROLE_REGULAR_USER";
	public static final String ADMIN_USER = "ROLE_ADMIN_USER";

	private String authority;

	public static Optional<Role> of(String role) {
		if (Objects.isNull(role) || role.isBlank()) {
			return Optional.empty();
		}
		switch (role) {
		case REGULAR_USER:
		case "REGULAR_USER":
			return Optional.of(new Role(REGULAR_USER));
		case ADMIN_USER:
		case "ADMIN_USER":
			return Optional.of(new Role(ADMIN_USER));
		}
		return Optional.empty();
	}
}
