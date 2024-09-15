package com.yago.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yago.enums.Category;
import com.yago.enums.converts.conversorCategory;
import com.yago.model.Course;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;


public record CourseDTO(@JsonProperty("_id")
                        Long id,

                        @NotBlank
                        @Length(min = 5, max = 200)
                        String name,

                        @NotNull
                        @Convert(converter = conversorCategory.class)
                        Category category,

                        @NotNull
                        @NotEmpty
                        @Valid
                        List<LessonDTO> lessons
                        )
{
    public CourseDTO(Course course)
    {
      this(course.getId(), course.getName(), course.getCategory(),
              course.getLessons().stream().map(LessonDTO::new).toList());
    }
}
