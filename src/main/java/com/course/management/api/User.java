package com.course.management.api;

import com.course.management.domain.AppUser;
import com.course.management.dto.RoleDTO;
import com.course.management.dto.UserDTO;
import com.course.management.service.interfaces.IUserService;
import com.course.management.domain.Role;
import com.course.management.dto.RoleToUserDTO;
import com.course.management.utility.ResponseHandler;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@CrossOrigin
@RestController
@SecurityRequirement(name = "demo-api")
@RequiredArgsConstructor
@RequestMapping("api/v1")

public class User {

    private final IUserService IUserService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Retrieved Successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "404", description = "user not found",
                    content = @Content)})
    @GetMapping("/users")
    public ResponseEntity<Object> getUsers() {
        var data = IUserService.getUsers();
        return ResponseHandler.generateResponse("Retrieved all users", HttpStatus.OK, data);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Saved User",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    @PostMapping("/user/save")
    public ResponseEntity<Object> saveUser(@RequestBody AppUser appUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/save").toUriString());
        var data = IUserService.saveUser(appUser);
        return ResponseHandler.generateResponse("User Created", HttpStatus.OK, data);

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Saved Role",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveUser(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/role/save").toUriString());
        return ResponseEntity.created(uri).body(IUserService.saveRole(role));
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Added Role To User",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    @PostMapping("/add-role")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDTO dto) {
        IUserService.addRoleToUser(dto.getUsername(), dto.getPassword());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/refresh-token")
    public void refreshToken(HttpServletResponse request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
    }
}


