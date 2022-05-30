package com.course.management.api;

import com.course.management.dto.StudentDTO;
import com.course.management.service.implementations.StudentServiceImpl;
import com.course.management.service.interfaces.IStudentService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@SecurityRequirement(name = "demo-api")
@RequestMapping(path = "api/v1/student")
public class Student {

    private final IStudentService studentService;

    @Autowired
    public Student(StudentServiceImpl studentServiceImpl) {
        this.studentService = studentServiceImpl;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students Retrieved Successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentDTO.class))}),
            @ApiResponse(responseCode = "404", description = "No Record Found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    @GetMapping
    public List<com.course.management.domain.Student> getStudents() {
        return studentService.getStudents();

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Students Saved Successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    @PostMapping()
    public void registerNewStudent(@RequestBody StudentDTO studentDTO) {
        studentService.addNewStudent(studentDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Students Saved Successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Id not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students Updated Successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Student Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ) {
        studentService.updateStudent(studentId, name, email);
    }
}
