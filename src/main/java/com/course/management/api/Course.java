package com.course.management.api;

import com.course.management.dto.CourseDTO;
import com.course.management.dto.UserDTO;
import com.course.management.service.interfaces.ICourseService;
import com.course.management.utility.ResponseHandler;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@SecurityRequirement(name = "demo-api")
@RequestMapping(path = "api/v1")
public class Course {
    ICourseService courseService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Courses Retrieved Successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "course not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @GetMapping("/courses")
    public ResponseEntity<Object> getCourses() {
        List<CourseDTO> data = courseService.getCourses();
        return ResponseHandler.generateResponse("Retrieved all courses", HttpStatus.OK, data);
    }
}
