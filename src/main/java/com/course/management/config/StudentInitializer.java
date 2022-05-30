package com.course.management.config;
import com.course.management.dto.StudentDTO;
import com.course.management.service.implementations.StudentServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration

public class StudentInitializer {

    @Bean
    CommandLineRunner commandLineRunner(StudentServiceImpl studentServiceImpl) {
        return args -> {
            studentServiceImpl.addNewStudent(new StudentDTO("Francis", LocalDate.of(1989, Month.JANUARY, 15), "francis@gmail.com"));
            studentServiceImpl.addNewStudent(new StudentDTO("Henry", LocalDate.of(2000, Month.FEBRUARY, 25), "henry@gmail.com"));
        };


    };

}
