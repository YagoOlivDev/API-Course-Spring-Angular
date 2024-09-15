package com.yago.repository;


import com.yago.dto.CourseDTO;
import com.yago.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Observable;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>
{


}
