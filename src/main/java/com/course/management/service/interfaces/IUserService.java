package com.course.management.service.interfaces;

import com.course.management.domain.AppUser;
import com.course.management.domain.Role;
import com.course.management.dto.UserDTO;

import java.util.List;

public interface IUserService {

    AppUser saveUser(AppUser appUser);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    UserDTO getUser(String username);

    List<UserDTO> getUsers();
}
