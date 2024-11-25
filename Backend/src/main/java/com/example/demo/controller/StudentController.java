package com.example.demo.controller;

import com.example.demo.dto.request.RegisterDTO;
import com.example.demo.model.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    private UserService userService;

    @PutMapping("update/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody RegisterDTO userDTO) {
        try {
            UserEntity updatedUser = userService.updateUser(id, userDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
