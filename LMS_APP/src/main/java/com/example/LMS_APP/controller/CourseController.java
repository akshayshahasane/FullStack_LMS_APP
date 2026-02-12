package com.example.LMS_APP.controller;

import com.example.LMS_APP.dto.CourseRequest;
import com.example.LMS_APP.entity.Course;
import com.example.LMS_APP.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;

    // Instructor only
    @PostMapping("/instructor/courses")
    public String create(@RequestBody CourseRequest request){
        return courseService.createCourse(request);
    }

    // Logged users
    @GetMapping("/student/courses")
    public List<Course> getCourses(){
        return courseService.getAllCourses();
    }

    // Admin only
    @DeleteMapping("/admin/courses/{id}")
    public String delete(@PathVariable Long id){
        return courseService.deleteCourse(id);
    }
}
