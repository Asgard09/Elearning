package com.example.student_dashboard.BE.model;

import jakarta.persistence.*;

@Entity
@Table(name="student_courses")
public class Student_courses {

    public Student_courses() {

    }

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "student_id")
    private int student_id;
}