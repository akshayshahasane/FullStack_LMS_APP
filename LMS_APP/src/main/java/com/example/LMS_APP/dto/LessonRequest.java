package com.example.LMS_APP.dto;

import lombok.Data;

@Data
public class LessonRequest {

    private String title;
    private String content;      // Text / PDF link
    private String videoUrl;     // Video link
    private Integer lessonOrder;
    private Long courseId;      // Must belong to a course

}