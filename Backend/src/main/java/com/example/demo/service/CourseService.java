package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Enrollment;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public Course createCourse(Course course) {
        course.setCreateAt(new Date());
        course.setUpdateAt(new Date());
        return courseRepository.save(course);
    }

    public List<String> getAllCourseNames() {
        List<Course> courses = courseRepository.findAll();
        List<String> courseNames = new ArrayList<>();
        for (Course course : courses) {
            courseNames.add(course.getCourseName());
        }
        return courseNames;
    }

    public Optional<Course> getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    public boolean enrollStudentInCourse(Enrollment enrollment) {
        Optional<UserEntity> studentOpt = userRepository.findById(enrollment.getUser().getUserId());
        Optional<Course> courseOpt = courseRepository.findById(enrollment.getCourse().getCourseId());

        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            enrollment.setEnrollAt(new Date());
            enrollmentRepository.save(enrollment);
            return true;
        }
        return false;
    }

    public Course updateCourse(Long courseId, Course updatedCourse) {
        Optional<Course> existingCourseOpt = courseRepository.findById(courseId);
        if (existingCourseOpt.isPresent()) {
            Course existingCourse = existingCourseOpt.get();
            existingCourse.setCourseName(updatedCourse.getCourseName());
            existingCourse.setDescription(updatedCourse.getDescription());
            existingCourse.setInstructorId(updatedCourse.getInstructorId());
            existingCourse.setUpdateAt(new Date());
            existingCourse.setCourseStatus(updatedCourse.getCourseStatus());
            return courseRepository.save(existingCourse);
        }
        return null;
    }

    public boolean completeCourse(Long userId, Long courseId) {
        Enrollment enrollment = enrollmentRepository.findByUserIdAndCourseId(userId, courseId);
        if (enrollment != null) {
            enrollment.setProgress(100.0); // Đánh dấu khóa học là hoàn thành
            enrollmentRepository.save(enrollment);
            return true;
        }
        return false; // Enrollment not found
    }

    public List<String> getStudentProgressInCourse(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);
        List<String> progressInfo = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            String info = "Student ID: " + enrollment.getUser().getUserId() +
                    ", Progress: " + enrollment.getProgress() + "%";
            progressInfo.add(info);
        }
        return progressInfo;
    }

    // ... existing code ...

    public boolean deleteCourse(Long courseId) {
        Optional<Course> existingCourseOpt = courseRepository.findById(courseId);
        if (existingCourseOpt.isPresent()) {
            courseRepository.delete(existingCourseOpt.get());
            return true;
        }
        return false; // Course not found
    }
}
