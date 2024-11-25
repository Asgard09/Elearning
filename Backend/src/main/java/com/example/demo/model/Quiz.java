package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long quizId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "choices")
    private String choices;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "field_difficult")
    private String fieldDifficult;
}
