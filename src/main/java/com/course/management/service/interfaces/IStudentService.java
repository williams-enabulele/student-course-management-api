package com.course.management.service.interfaces;

import com.course.management.domain.Student;
import com.course.management.dto.StudentDTO;

import java.util.List;

public interface IStudentService {
    public List<Student> getStudents();
    public void addNewStudent(StudentDTO studentDTO);
    public void deleteStudent(Long studentId);
    public void updateStudent(Long studentId, String name, String email);
}
