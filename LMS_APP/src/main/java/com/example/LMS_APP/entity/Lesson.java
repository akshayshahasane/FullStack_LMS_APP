package com.example.LMS_APP.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String content;  // Text description or PDF link

    private String videoUrl;  // Video link (YouTube/S3/etc)

    private Integer lessonOrder; // Optional ordering

    // Many lessons have one course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
