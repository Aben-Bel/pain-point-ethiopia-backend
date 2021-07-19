package com.painpoint.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class User implements UserDetails {

	private static final long serialVersionUID = 3943511987040606336L;

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	@Column(nullable = false, length = 128, unique = true)
	private String username;

	@NonNull
	@Size(min = 4, message = "Password should be minimum 6 characters long")
	@Column(nullable = false)
	private String password;

	@NonNull
	@NotBlank(message = "full name is mandatory")
	@Column(nullable = false)
	private String fullName;

	@Column
	@ElementCollection(targetClass = Role.class)
	private Set<Role> authorities = new HashSet<>();
	private boolean enabled = true;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.enabled = true;
	}
	@Override
	public boolean isAccountNonExpired() {
		return enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return enabled;
	}

}
