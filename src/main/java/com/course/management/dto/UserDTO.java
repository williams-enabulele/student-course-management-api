package com.course.management.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
@Data
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private Collection<RoleDTO> roles = new ArrayList<RoleDTO>();
}
