package com.example.LMS_APP.service;

import com.example.LMS_APP.entity.Lesson;
import com.example.LMS_APP.entity.Progress;
import com.example.LMS_APP.entity.User;
import com.example.LMS_APP.repository.LessonRepository;
import com.example.LMS_APP.repository.ProgressRepository;
import com.example.LMS_APP.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

    // Mark lesson as completed
    public String completeLesson(Long lessonId){

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User student = userRepository.findByEmail(email).orElseThrow();

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        // Prevent duplicate
        if(progressRepository.findByStudentAndLesson(student, lesson).isPresent()){
            return "Lesson already completed";
        }

        Progress progress = Progress.builder()
                .student(student)
                .lesson(lesson)
                .completedAt(LocalDateTime.now())
                .build();

        progressRepository.save(progress);
        return "Lesson marked as completed!";
    }

    // Get course completion percentage
    public double getCourseProgress(Long courseId){

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User student = userRepository.findByEmail(email).orElseThrow();

        List<Progress> completed = progressRepository.findByStudentAndLessonCourseId(student, courseId);
        List<Lesson> totalLessons = lessonRepository.findByCourseOrderByLessonOrderAsc(
                lessonRepository.findById(courseId)
                        .orElseThrow(() -> new RuntimeException("Course not found"))
                        .getCourse()
        );

        if(totalLessons.isEmpty()) return 0;

        return ((double) completed.size() / totalLessons.size()) * 100;
    }
}
