package com.example.LMS_APP.repository;

import com.example.LMS_APP.entity.Lesson;
import com.example.LMS_APP.entity.Progress;
import com.example.LMS_APP.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {

    Optional<Progress> findByStudentAndLesson(User student, Lesson lesson);

    List<Progress> findByStudentAndLessonCourseId(User student, Long courseId);
}
