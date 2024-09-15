package com.yago.service;

import com.yago.dto.CourseDTO;
import com.yago.exception.NotFoundExeception;
import com.yago.model.Course;
import com.yago.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class CourseService
{
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository)
    {
        this.courseRepository = courseRepository;
    }

    public List<CourseDTO> listar()
    {
        return courseRepository.findAll().stream().map(CourseDTO::new).toList();
    }

    public CourseDTO findById(@PathVariable @NotNull @Positive Long id)
    {
        return courseRepository.findById(id).map(CourseDTO::new)
                .orElseThrow(() -> new NotFoundExeception(id));

    }

    public Course create(@RequestBody @Valid CourseDTO courseDto)
    {
        var course = new Course(courseDto);
        return courseRepository.save(course);
    }

    @Transactional
    public CourseDTO updateCourse(@Valid CourseDTO courseDto)
    {
        var course = courseRepository.getReferenceById(courseDto.id());
        course.updateInformation(courseDto);
        System.out.println(course.getName() + course.getCategory());

        return new CourseDTO(course);
    }

    public void removeCourse(@PathVariable @NotNull @Positive Long id)
    {
        var cursoEncontrado = courseRepository.findById(id).orElseThrow
                (() -> new NotFoundExeception(id));

        courseRepository.deleteById(cursoEncontrado.getId());
    }
}
