package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "type")
    private String materialType;

    @Column(name = "uploaded_at")
    private Date uploadedAt;
}