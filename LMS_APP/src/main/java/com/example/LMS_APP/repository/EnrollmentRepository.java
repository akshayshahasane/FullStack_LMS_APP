package com.example.LMS_APP.repository;

import com.example.LMS_APP.entity.Course;
import com.example.LMS_APP.entity.Enrollment;
import com.example.LMS_APP.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudent(User student);

    Optional<Enrollment> findByStudentAndCourse(User student, Course course);
}
