package com.yago;

import com.yago.enums.Category;
import com.yago.model.Course;
import com.yago.model.Lesson;
import com.yago.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;


@SpringBootApplication
public class CrudSpringApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository)
	{
		return args ->
		{
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular com Spring ");
			c.setCategory(Category.BACKEND);
			System.out.println("Até aqui tudo bem (1)");

			Lesson l = new Lesson();
			l.setName("Introdução");
			l.setYoutubeUrl("01234567890");
			l.setCourse(c);
			c.getLessons().add(l);
			System.out.println("Até aqui tudo bem (2)");

			courseRepository.save(c);
			System.out.println("Até aqui tudo bem (3)");
		};
	}
}
