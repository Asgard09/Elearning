package com.example.student_dashboard.BE.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int id;

    @Column(name = "student_name")
    private String student_name;

    @Column(name = "student_password")
    private String student_password;
}