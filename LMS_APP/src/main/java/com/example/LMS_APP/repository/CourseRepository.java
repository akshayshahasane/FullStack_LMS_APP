package com.example.LMS_APP.repository;

import com.example.LMS_APP.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
