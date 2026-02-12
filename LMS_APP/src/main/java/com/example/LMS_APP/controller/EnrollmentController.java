package com.example.LMS_APP.controller;

import com.example.LMS_APP.entity.Enrollment;
import com.example.LMS_APP.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    // Enroll in course
    @PostMapping("/enroll/{courseId}")
    public String enroll(@PathVariable Long courseId){
        return enrollmentService.enroll(courseId);
    }

    // View my courses
    @GetMapping("/my-courses")
    public List<Enrollment> myCourses(){
        return enrollmentService.getMyCourses();
    }
}
