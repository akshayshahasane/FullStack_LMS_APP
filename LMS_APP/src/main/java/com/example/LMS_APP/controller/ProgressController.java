package com.example.LMS_APP.controller;

import com.example.LMS_APP.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    // Mark lesson as completed
    @PostMapping("/lessons/{lessonId}/complete")
    public String completeLesson(@PathVariable Long lessonId){
        return progressService.completeLesson(lessonId);
    }

    // Get course completion %
    @GetMapping("/courses/{courseId}/progress")
    public double courseProgress(@PathVariable Long courseId){
        return progressService.getCourseProgress(courseId);
    }
}

