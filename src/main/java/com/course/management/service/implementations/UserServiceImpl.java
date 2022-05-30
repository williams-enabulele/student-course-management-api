package com.course.management.service.implementations;

import com.course.management.domain.AppUser;
import com.course.management.service.interfaces.IUserService;
import com.course.management.dto.UserDTO;
import com.course.management.repository.UserRepository;
import com.course.management.repository.RoleRepository;
import com.course.management.domain.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements IUserService, UserDetailsService, Serializable {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public AppUser saveUser(AppUser appUser) {
        log.info("Saving user {} into the database", appUser.getName());
        appUser.setPassword(this.passwordEncoder.encode(appUser.getPassword()));
        return userRepository.save(appUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving role {} into the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

        AppUser appUser = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        log.info("Assigning user {} with role {}", appUser.getName(), role.getName());
        appUser.getRoles().add(role);
    }

    @Override
    public UserDTO getUser(String username) {
        log.info("Fetch one user from database");
        AppUser appUser = userRepository.findByUsername(username);
        UserDTO response = modelMapper.map(appUser, UserDTO.class);
        return  response;
    }

    @Override
    public List<UserDTO> getUsers() {
        log.info("Fetching all users from database");
        List<AppUser> user  =  userRepository.findAll();
        return user.stream().map(userEntity -> modelMapper.map(userEntity, UserDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser AppUser = userRepository.findByUsername(username);
        if (AppUser == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found in database {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        AppUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(AppUser.getUsername(), AppUser.getPassword(), authorities);
    }


}
