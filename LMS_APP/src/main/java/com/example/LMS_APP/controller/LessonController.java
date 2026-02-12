package com.example.LMS_APP.controller;

import com.example.LMS_APP.dto.LessonRequest;
import com.example.LMS_APP.entity.Lesson;
import com.example.LMS_APP.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    // Instructor adds lesson
    @PostMapping("/instructor/lessons")
    public String addLesson(@RequestBody LessonRequest request){
        return lessonService.addLesson(request);
    }

    // Student views lessons for a course
    @GetMapping("/student/courses/{courseId}/lessons")
    public List<Lesson> getLessons(@PathVariable Long courseId){
        return lessonService.getLessonsByCourse(courseId);
    }
}
