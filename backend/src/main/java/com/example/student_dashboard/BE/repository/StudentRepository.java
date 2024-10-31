package com.example.student_dashboard.BE.repository;

import com.example.student_dashboard.BE.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE s.student_password = :password AND s.student_name = :name")
    Student findByPasswordAndName(@Param("password") String password, @Param("name") String name);
}