package com.course.management.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Student> students = new ArrayList<Student>();
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Course> courses = new ArrayList<Course>();
}
