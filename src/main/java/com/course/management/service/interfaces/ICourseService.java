package com.course.management.service.interfaces;

import com.course.management.dto.CourseDTO;

import java.util.List;

public interface ICourseService {
    CourseDTO saveCourse(CourseDTO courseDTO);

    CourseDTO getCourse(Long id);

    boolean updateCourse(Long id, CourseDTO courseDTO);

    boolean deleteCourse(Long id);

    List<CourseDTO> getCourses();
}
