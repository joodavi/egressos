package br.ufma.egressos.model;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.egressos.model.repository.CourseRepository;
import br.ufma.egressos.model.repository.GraduateCourseRepository;
import br.ufma.egressos.model.repository.GraduateRepository;
import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class CourseGraduateTest {
    @Autowired
    GraduateCourseRepository repository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    GraduateRepository graduateRepository;

    @Test
    public void shouldSaveGraduateCourse() {
        LocalDate data = LocalDate.now();

        Course course = Course.builder().name("Curso 1").level("Nível 1").build();

        Graduate graduate = Graduate.builder().name("Name 1").email("email@email.com").cpf("01234567890").resume("Resume 1").urlPhoto("url_photo").build();

        Course courseSaved = courseRepository.save(course);
        Graduate graduateSaved = graduateRepository.save(graduate);

        GraduateCoursePK idGraduateCourse = new GraduateCoursePK(courseSaved.getId(), graduateSaved.getId());

        GraduateCourse graduateCourse = GraduateCourse.builder().id(idGraduateCourse).course(course).graduate(graduate).initialDate(data).finishDate(data).build();

        GraduateCourse graduateCourseSaved = repository.save(graduateCourse);

        Assertions.assertNotNull(graduateCourseSaved);
        Assertions.assertEquals(graduateCourse.getCourse().getName(), graduateCourseSaved.getCourse().getName());
        Assertions.assertEquals(graduateCourse.getGraduate().getCpf(), graduateCourseSaved.getGraduate().getCpf());
        Assertions.assertEquals(graduateCourse.getInitialDate(), graduateCourseSaved.getInitialDate());
        Assertions.assertEquals(graduateCourse.getFinishDate(), graduateCourseSaved.getFinishDate());
    }

    @Test
    public void shouldFindGraduateCourse() {
        LocalDate data = LocalDate.now();

        Course course = Course.builder().name("Curso 1").level("Nível 1").build();

        Graduate graduate = Graduate.builder().name("Name 1").email("email@email.com").cpf("01234567890").resume("Resume 1").urlPhoto("url_photo").build();

        Course courseSaved = courseRepository.save(course);
        Graduate graduateSaved = graduateRepository.save(graduate);

        GraduateCoursePK idGraduateCourse = new GraduateCoursePK(courseSaved.getId(), graduateSaved.getId());

        GraduateCourse graduateCourse = GraduateCourse.builder().id(idGraduateCourse).course(course).graduate(graduate).initialDate(data).finishDate(data).build();

        GraduateCourse graduateCourseSaved = repository.save(graduateCourse);

        Optional<GraduateCourse> graduateCourseTest = repository.findById(graduateCourseSaved.getId());

        Assertions.assertNotNull(graduateCourseTest);
        Assertions.assertEquals(graduateCourseSaved.getCourse().getId(), graduateCourseTest.get().getCourse().getId());
        Assertions.assertEquals(graduateCourseSaved.getGraduate().getId(), graduateCourseTest.get().getGraduate().getId());
        Assertions.assertEquals(graduateCourseSaved.getInitialDate(), graduateCourseTest.get().getInitialDate());
        Assertions.assertEquals(graduateCourseSaved.getFinishDate(), graduateCourseTest.get().getFinishDate());
    }

    @Test
    public void shouldUpdateGraduateCourse() {
        LocalDate data = LocalDate.now();

        Course course = Course.builder().name("Curso 1").level("Nível 1").build();
        Course course2 = Course.builder().name("Curso 2").level("Nível 2").build();

        Graduate graduate = Graduate.builder().name("Name 1").email("email@email.com").cpf("01234567890").resume("Resume 1").urlPhoto("url_photo").build();

        Course courseSaved = courseRepository.save(course);
        Graduate graduateSaved = graduateRepository.save(graduate);

        GraduateCoursePK idGraduateCourse = new GraduateCoursePK(courseSaved.getId(), graduateSaved.getId());

        GraduateCourse graduateCourse = GraduateCourse.builder().id(idGraduateCourse).course(course).graduate(graduate).initialDate(data).finishDate(data).build();

        GraduateCourse graduateCourseSaved = repository.save(graduateCourse);

        graduateCourseSaved.setCourse(course2);

        GraduateCourse graduateCourseUpdate = repository.save(graduateCourseSaved);

        Assertions.assertNotNull(graduateCourseUpdate);
        Assertions.assertFalse(graduateCourseSaved.getGraduate().getName().equals("Curso 1"));
        Assertions.assertEquals(graduateCourseSaved.getCourse().getName(), "Curso 2");
    }

    @Test
    public void shoulDeleteGraduateCourse() {
        LocalDate data = LocalDate.now();

        Course course = Course.builder().name("Curso 1").level("Nível 1").build();

        Graduate graduate = Graduate.builder().name("Name 1").email("email@email.com").cpf("01234567890").resume("Resume 1").urlPhoto("url_photo").build();

        Course courseSaved = courseRepository.save(course);
        Graduate graduateSaved = graduateRepository.save(graduate);

        GraduateCoursePK idGraduateCourse = new GraduateCoursePK(courseSaved.getId(), graduateSaved.getId());

        GraduateCourse graduateCourse = GraduateCourse.builder().id(idGraduateCourse).course(course).graduate(graduate).initialDate(data).finishDate(data).build();

        GraduateCourse graduateCourseSaved = repository.save(graduateCourse);
        repository.delete(graduateCourseSaved);

        Optional<GraduateCourse> test = repository.findById(graduateCourseSaved.getId());
        Assertions.assertTrue(test.isEmpty());
    }
}
