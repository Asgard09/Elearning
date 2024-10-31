package com.example.student_dashboard.BE.service;

import com.example.student_dashboard.BE.model.Student;
import com.example.student_dashboard.BE.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student Login(String student_name, String student_password)
    {
        return studentRepository.findByPasswordAndName(student_password, student_name);
    }
    public Student SignUp(Student student) {
        return studentRepository.save(student);
    }

}
