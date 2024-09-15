package com.yago.controller;

import com.yago.dto.CourseDTO;
import com.yago.repository.CourseRepository;
import com.yago.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController
{
    private final CourseRepository courseRepository;
    private final CourseService courseService;

    @GetMapping
    public @ResponseBody ResponseEntity<List<CourseDTO>> listar()
    {
        var cursos = courseService.listar();
        return ResponseEntity.ok(cursos);
    }


    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<CourseDTO> findByID(@PathVariable @NotNull @Positive Long id)
    {
        var curso = courseService.findById(id);
        return ResponseEntity.ok(curso);
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create(@RequestBody @Valid CourseDTO courseDto)
    {
        courseService.create(courseDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody @Valid CourseDTO courseDto)
    {
        var course = courseService.updateCourse(courseDto);
        return ResponseEntity.ok(course);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity removeCourse(@PathVariable @NotNull @Positive Long id)
    {
        courseService.removeCourse(id);
        return ResponseEntity.noContent().build();
    }

}

