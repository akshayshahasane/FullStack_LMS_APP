package com.example.LMS_APP.repository;

import com.example.LMS_APP.entity.Course;
import com.example.LMS_APP.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByCourseOrderByLessonOrderAsc(Course course);
}
