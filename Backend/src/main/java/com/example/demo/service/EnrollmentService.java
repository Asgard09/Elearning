package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Enrollment;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Enrollment enrollStudent(Long userId, Long courseId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (user == null || course == null) {
            return null; // Handle not found
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrollAt(new Date());
        return enrollmentRepository.save(enrollment);
    }

    public boolean completeCourse(Long userId, Long courseId) {
        // Tìm kiếm Enrollment dựa trên userId và courseId
        Enrollment enrollment = enrollmentRepository.findByUserIdAndCourseId(userId, courseId);
        if (enrollment != null) {
            enrollment.setProgress(100.0); // Đánh dấu khóa học là hoàn thành
            enrollment.setStudyStatus("COMPLETE");
            enrollmentRepository.save(enrollment); // Lưu lại thay đổi
            return true; // Trả về true nếu hoàn thành thành công
        }
        return false; // Trả về false nếu không tìm thấy Enrollment
    }
}
