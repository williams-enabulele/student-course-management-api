package com.course.management.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseEntity {
    private String name;
    private LocalDate dob;
    private String email;
    @Transient
    private Integer age;


}

