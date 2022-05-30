package com.course.management.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {
    @NotBlank
    @Min(3)
    private String username;
    @NotBlank
    private String password;
}
