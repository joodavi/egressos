package br.ufma.egressos.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.egressos.model.repository.GraduateRepository;
import br.ufma.egressos.model.repository.TestimonyRepository;
import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class TestimonyTest {
    @Autowired
    TestimonyRepository repository;

    @Autowired
    GraduateRepository graduateRepository;

    @Test
    public void shouldSaveTestimony() {
        LocalDate date = LocalDate.now();

        Graduate graduate = Graduate.builder().name("Name 1").email("email@email.com").cpf("01234567890").resume("Resume 1").urlPhoto("url_Photo").build();

        Testimony testimony = Testimony.builder().graduate(graduate).text("Text").date(date).build();

        List<Testimony> testimonies = new ArrayList<>();
        testimonies.add(testimony);

        graduate.setTestimonies(testimonies);
        Graduate graduateSaved = graduateRepository.save(graduate);

        Assertions.assertNotNull(graduateSaved);
        Assertions.assertEquals(testimony.getText(), testimonies.get(0).getText());
        Assertions.assertEquals(testimony.getText(), graduateSaved.getTestimonies().get(0).getText());
    }

    @Test
    public void shouldUpdateTestimony() {
        
        LocalDate date = LocalDate.now();

        Graduate graduate = Graduate.builder().name("Name 1").email("email@email.com").cpf("01234567890").resume("Resume 1").urlPhoto("url_Photo").build();

        Testimony testimony = Testimony.builder().graduate(graduate).text("Text").date(date).build();

        List<Testimony> testimonies = new ArrayList<>();
        testimonies.add(testimony);

        graduate.setTestimonies(testimonies);
        Graduate graduateSaved = graduateRepository.save(graduate);

        graduateSaved.getTestimonies().get(0).setText("Text 2");

        Assertions.assertNotNull(graduateSaved);
        Assertions.assertFalse(graduateSaved.getTestimonies().get(0).getText().equals("Text"));
        Assertions.assertEquals(graduateSaved.getTestimonies().get(0).getText(), "Text 2");
    }

    @Test
    public void shouldDeleteTestimony() {
        LocalDate date = LocalDate.now();
        Testimony testimony = Testimony.builder().text("Text").date(date).build();

        Testimony testimonySaved = repository.save(testimony);
        UUID id = testimonySaved.getId();
        repository.deleteById(id);

        Optional<Testimony> test = repository.findById(id);
        Assertions.assertTrue(test.isEmpty());
    }
}
