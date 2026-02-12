package com.example.LMS_APP.service;

import com.example.LMS_APP.dto.CourseRequest;
import com.example.LMS_APP.entity.Course;
import com.example.LMS_APP.entity.User;
import com.example.LMS_APP.repository.CourseRepository;
import com.example.LMS_APP.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    // Instructor creates course
    public String createCourse(CourseRequest request){

        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        User instructor = userRepository.findByEmail(email)
                .orElseThrow();

        Course course = Course.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .createdAt(LocalDateTime.now())
                .instructor(instructor)
                .build();

        courseRepository.save(course);

        return "Course created successfully!";
    }

    // Everyone can view
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    // Admin deletes
    public String deleteCourse(Long id){
        courseRepository.deleteById(id);
        return "Course deleted!";
    }
}
