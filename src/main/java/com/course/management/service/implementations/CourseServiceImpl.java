package com.course.management.service.implementations;

import com.course.management.domain.Course;
import com.course.management.dto.CourseDTO;
import com.course.management.repository.CourseRepository;
import com.course.management.service.interfaces.ICourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements ICourseService {
    CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        Course course = modelMapper.map(courseDTO, Course.class);
        var response = courseRepository.save(course);
        return  modelMapper.map(response, CourseDTO.class);

    }

    @Override
    public CourseDTO getCourse(Long id) {
       Optional<Course> course = courseRepository.findById(id);
       return modelMapper.map(course,CourseDTO.class);
    }

    @Override
    public boolean updateCourse(Long id, CourseDTO courseDTO) {
        return false;
    }

    @Override
    public boolean deleteCourse(Long id) {
        return false;
    }

    @Override
    public List<CourseDTO> getCourses() {
        return null;
    }
}
