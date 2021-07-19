package com.painpoint.domain.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateUserRequest {

    @NotBlank
    private String fullName;

}
