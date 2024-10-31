package com.example.student_dashboard.BE.controller;

import com.example.student_dashboard.BE.model.Student;
import com.example.student_dashboard.BE.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/login/{student_name}/{student_password}")
    public ResponseEntity<?> login(@PathVariable String student_name,
                                   @PathVariable String student_password) {
        Student student = studentService.Login(student_name, student_password);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.badRequest().body("Invalid credentials");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Student student) {
        Student newStudent = studentService.SignUp(student);
        if (newStudent != null) {
            return ResponseEntity.ok(newStudent);
        }
        return ResponseEntity.badRequest().body("User already exists");
    }
}