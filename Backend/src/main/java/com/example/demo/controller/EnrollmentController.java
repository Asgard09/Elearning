package com.example.demo.controller;

import com.example.demo.model.Enrollment;
import com.example.demo.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("student/enroll/{studentId}/{courseId}")
    public ResponseEntity<Enrollment> enrollStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);

        if (enrollment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Handle not found
        }

        return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
    }


    // API để hoàn thành khóa học
    @PostMapping("/complete/{userId}/{courseId}")
    public ResponseEntity<String> completeCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        boolean isCompleted = enrollmentService.completeCourse(userId, courseId);

        if (!isCompleted) {
            return new ResponseEntity<>("Enrollment not found", HttpStatus.NOT_FOUND); // Handle not found
        }

        return new ResponseEntity<>("Course completed successfully", HttpStatus.OK);
    }
}
