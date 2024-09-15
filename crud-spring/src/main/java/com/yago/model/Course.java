package com.yago.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.yago.dto.CourseDTO;
import com.yago.enums.Category;
import com.yago.enums.Status;
import com.yago.enums.converts.conversorCategory;
import com.yago.enums.converts.conversorStatus;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity(name = "courses")
@SQLDelete(sql = "UPDATE courses SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @Length(min = 5, max = 200)
    @Column(length = 200, nullable = false)
    private String name;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = conversorCategory.class)
    private Category category;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = conversorStatus.class)
    private Status status = Status.ATIVO;

    @NotNull
    @NotEmpty
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course", fetch = FetchType.EAGER)
    private List<Lesson> lessons = new ArrayList<>();


    public Course(CourseDTO courseDto)
    {
        this.name=courseDto.name();
        this.category=courseDto.category();
        this.lessons=courseDto.lessons().stream().map(lessonDto -> {
                    Lesson lesson = new Lesson(lessonDto);
                    lesson.setCourse(this);
                    return lesson;
                }).collect(Collectors.toList());
    }

    public Course() {}


    private void excluirCourse(Long id)
    {

    }

    public void updateInformation(CourseDTO courseDto)
    {
        this.name = courseDto.name();
        this.category = courseDto.category();

        this.lessons.clear();

        courseDto.lessons().forEach(lessonDto -> {
            Lesson lesson = new Lesson();
            lesson.setName(lessonDto.name());
            lesson.setYoutubeUrl(lessonDto.youtubeUrl());
            lesson.setCourse(this);
            this.lessons.add(lesson);
        });
    }
}
