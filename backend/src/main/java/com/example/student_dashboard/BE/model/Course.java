package com.example.student_dashboard.BE.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long courseId;

    @Column(name = "course_name")
    @NotBlank
    private String courseName;

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "start_date")
    @NotBlank
    private Date startDate;

    @Column(name = "end_date")
    @NotBlank
    private Date endDate;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Enrollment> enrollmentList;
}
