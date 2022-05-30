package com.course.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;
    @NotBlank
    private LocalDate dob;
    @NotBlank
    @Email
    private String email;

}
