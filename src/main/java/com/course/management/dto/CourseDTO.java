package com.course.management.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CourseDTO {
    private  Long id;
    @NotBlank
    private String title;
    private String description;
}
