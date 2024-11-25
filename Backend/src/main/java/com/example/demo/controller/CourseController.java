package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("admin/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllCourseNames() {
        List<String> courseNames = courseService.getAllCourseNames();
        return new ResponseEntity<>(courseNames, HttpStatus.OK);
    }

    @GetMapping("view/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("admin/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        Course course = courseService.updateCourse(id, updatedCourse);
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle not found
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("admin/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        boolean isDeleted = courseService.deleteCourse(id);
        if (isDeleted) {
            return new ResponseEntity<>("Delete Successfully", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Error",HttpStatus.NOT_FOUND); // Handle not found
    }
}
