package com.example.LMS_APP.service;

import com.example.LMS_APP.entity.Course;
import com.example.LMS_APP.entity.Enrollment;
import com.example.LMS_APP.entity.User;
import com.example.LMS_APP.repository.CourseRepository;
import com.example.LMS_APP.repository.EnrollmentRepository;
import com.example.LMS_APP.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    // Student enrolls in a course
    public String enroll(Long courseId){

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Prevent duplicate enrollment
        if(enrollmentRepository.findByStudentAndCourse(student, course).isPresent()){
            return "Already enrolled in this course";
        }

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .enrolledAt(LocalDateTime.now())
                .build();

        enrollmentRepository.save(enrollment);

        return "Enrolled successfully!";
    }

    // Get all courses student enrolled in
    public List<Enrollment> getMyCourses(){

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return enrollmentRepository.findByStudent(student);
    }
}
