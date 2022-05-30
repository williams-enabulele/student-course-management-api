package com.course.management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@SecurityScheme(name = "demo-api", scheme = "bearer", bearerFormat = "JWT", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(info =
@Info(
		title = "Course Management API",
		version = "1.0",
		description = "Manages student and courses registered.",
		license = @License(name = "Apache 2.0", url = "http://springdoc.org")))
public class CourseApplication {

@Bean
public ModelMapper modelMapper(){
	return new ModelMapper();
}

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

}
