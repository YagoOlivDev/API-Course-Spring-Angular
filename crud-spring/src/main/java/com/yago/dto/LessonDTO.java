package com.yago.dto;


import com.yago.model.Lesson;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;


public record LessonDTO (Long id,

                         @NotBlank
                         @Length(min = 5, max = 100)
                         String name,

                         @NotBlank
                         @Length(min = 10, max = 11)
                         String youtubeUrl)

{
    public LessonDTO(Lesson lesson)
    {
        this(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl());
    }
}
