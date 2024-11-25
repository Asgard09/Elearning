package com.example.demo.service;

import com.example.demo.dto.request.RegisterDTO;
import com.example.demo.model.Role;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public UserEntity signup(RegisterDTO newUser) {
        UserEntity user = new UserEntity();
        user.setEmail(newUser.getEmail());
        user.setUserName(newUser.getName());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        Role roles = roleRepository.findByName("STUDENT").get();
        user.setRoles(Collections.singletonList(roles));

        return userRepository.save(user);
    }

    public UserEntity updateUser(Long userId, RegisterDTO userDTO) {
        return userRepository.findById(userId).map(user -> {
            user.setUserName(userDTO.getName());
            if (!userRepository.existsByEmail(userDTO.getEmail())) {
                user.setEmail(userDTO.getEmail());
            }
            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
            user.setUpdatedAt(new Date());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + userId));
    }
}
