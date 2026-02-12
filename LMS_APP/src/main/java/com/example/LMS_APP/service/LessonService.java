package com.example.LMS_APP.service;

import com.example.LMS_APP.dto.LessonRequest;
import com.example.LMS_APP.entity.Course;
import com.example.LMS_APP.entity.Lesson;
import com.example.LMS_APP.entity.User;
import com.example.LMS_APP.repository.CourseRepository;
import com.example.LMS_APP.repository.LessonRepository;
import com.example.LMS_APP.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    // Instructor adds lesson
    public String addLesson(LessonRequest request){

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User instructor = userRepository.findByEmail(email).orElseThrow();

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Check if instructor owns the course
        if(!course.getInstructor().getId().equals(instructor.getId())){
            throw new RuntimeException("You are not the instructor of this course");
        }

        Lesson lesson = Lesson.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .videoUrl(request.getVideoUrl())
                .lessonOrder(request.getLessonOrder())
                .course(course)
                .build();

        lessonRepository.save(lesson);
        return "Lesson added successfully!";
    }

    // Get all lessons for a course
    public List<Lesson> getLessonsByCourse(Long courseId){

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return lessonRepository.findByCourseOrderByLessonOrderAsc(course);
    }
}
