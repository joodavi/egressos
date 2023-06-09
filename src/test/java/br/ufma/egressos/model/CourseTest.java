package br.ufma.egressos.model;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.egressos.model.repository.CourseRepository;
import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class CourseTest {
    @Autowired
    CourseRepository repository;

    @Test
    public void shouldSaveCourse() {
        Course course = Course.builder().name("Curso 1").level("Nível 1").build();

        Course courseSaved = repository.save(course);

        Assertions.assertNotNull(courseSaved);
        Assertions.assertEquals(course.getName(), courseSaved.getName());
        Assertions.assertEquals(course.getLevel(), courseSaved.getLevel());
    }

    @Test
    public void shouldFindCourseByName() {
        Course course = Course.builder().name("Curso 1").level("Nível 1").build();

        Course courseSaved = repository.save(course);

        Course courseResult = repository.findByName(courseSaved.getName());

        Assertions.assertNotNull(courseResult);
        Assertions.assertEquals(courseSaved.getName(), courseResult.getName());
    }

    @Test
    public void shouldUpdateCourse() {
        Course course = Course.builder().name("Curso 1").level("Nível 1").build();

        Course courseSaved = repository.save(course);

        courseSaved.setName("Curso 2");

        Assertions.assertNotNull(courseSaved);
        Assertions.assertFalse(courseSaved.getName().equals("Curso 1"));
        Assertions.assertEquals(courseSaved.getName(), "Curso 2");
    }

    @Test
    public void shouldDeleteCourse() {
        Course course = Course.builder().name("Curso 1").level("Nível 1").build();

        Course courseSaved = repository.save(course);
        UUID id = courseSaved.getId();
        repository.deleteById(id);

        Optional<Course> test = repository.findById(id);
        Assertions.assertTrue(test.isEmpty());
    }
}
