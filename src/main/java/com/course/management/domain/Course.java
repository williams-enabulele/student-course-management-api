package com.course.management.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity{
    private String title;
    private String description;
}
