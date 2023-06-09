package br.ufma.egressos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.egressos.model.repository.GraduateRepository;
import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class GraduateTest {
    @Autowired
    GraduateRepository repository;

    @Test
    public void shouldSaveGraduate() {
        Graduate graduate = Graduate.builder().name("Name 1").email("email@email.com").cpf("01234567890").resume("Resume 1").urlPhoto("url_photo").build();

        Graduate graduateSaved = repository.save(graduate);

        Assertions.assertNotNull(graduateSaved);
        Assertions.assertEquals(graduate.getName(), graduateSaved.getName());
        Assertions.assertEquals(graduate.getEmail(), graduateSaved.getEmail());
        Assertions.assertEquals(graduate.getCpf(), graduateSaved.getCpf());
        Assertions.assertEquals(graduate.getResume(), graduateSaved.getResume());
        Assertions.assertEquals(graduate.getUrlPhoto(), graduateSaved.getUrlPhoto());
    }

    @Test 
    public void shouldFindByName() {
        Graduate graduate1 = Graduate.builder().name("Name 1").email("email@email.com").cpf("01234567890").resume("Resume 1").urlPhoto("url_photo").build();
        
        Graduate graduate2 = Graduate.builder().name("Name 2").email("email@email.com").cpf("01234567890").resume("Resume 1").urlPhoto("url_photo").build();

        List<Graduate> graduates = new ArrayList<>();
        graduates.add(graduate1);
        graduates.add(graduate2);

        List<Graduate> graduateSaved = repository.saveAll(graduates);

        List<Graduate> test =  new ArrayList<>();
        for (Graduate graduate : graduates) {
            test.add(repository.findByName(graduate.getName()));
        }

        Assertions.assertNotNull(graduateSaved);
        Assertions.assertEquals(graduates.size(), test.size());
    }

    @Test 
    public void shouldFindByEmail() {
        Graduate graduate1 = Graduate.builder().name("Name 1").email("email@email.com").cpf("01234567890").resume("Resume 1").urlPhoto("url_photo").build();
        
        Graduate graduate2 = Graduate.builder().name("Name 2").email("email@gmail.com").cpf("01234567890").resume("Resume 1").urlPhoto("url_photo").build();

        List<Graduate> graduates = new ArrayList<>();
        graduates.add(graduate1);
        graduates.add(graduate2);

        List<Graduate> graduateSaved = repository.saveAll(graduates);

        List<Graduate> test =  new ArrayList<>();
        for (Graduate graduate : graduates) {
            test.add(repository.findByEmail(graduate.getEmail()));
        }

        Assertions.assertNotNull(graduateSaved);
        Assertions.assertEquals(graduates.size(), test.size());
    }

    @Test 
    public void shouldFindByCpf() {
        Graduate graduate1 = Graduate.builder().name("Name 1").email("email@email.com").cpf("01234567890").resume("Resume 1").urlPhoto("url_photo").build();
        
        Graduate graduate2 = Graduate.builder().name("Name 2").email("email@email.com").cpf("01234567891").resume("Resume 1").urlPhoto("url_photo").build();

        List<Graduate> graduates = new ArrayList<>();
        graduates.add(graduate1);
        graduates.add(graduate2);

        List<Graduate> graduateSaved = repository.saveAll(graduates);

        List<Graduate> test =  new ArrayList<>();
        for (Graduate graduate : graduates) {
            test.add(repository.findByCpf(graduate.getCpf()));
        }

        Assertions.assertNotNull(graduateSaved);
        Assertions.assertEquals(graduates.size(), test.size());
    }

    @Test
    public void shouldUpdateGraduate() {
        Graduate graduate = Graduate.builder().name("Name 1").email("email@email.com").cpf("01234567890").resume("Resume 1").urlPhoto("url_photo").build();

        Graduate graduateSaved = repository.save(graduate);

        graduateSaved.setEmail("email@gmail.com");

        Assertions.assertNotNull(graduateSaved);
        Assertions.assertFalse(graduateSaved.getEmail().equals("email@email.com"));
        Assertions.assertEquals(graduateSaved.getEmail(), "email@gmail.com");
    }

    @Test
    public void shouldDeleteGraduate() {
        Graduate graduate = Graduate.builder().name("Name 1").email("email@email.com").cpf("01234567890").resume("Resume 1").urlPhoto("url_photo").build();

        Graduate graduateSaved = repository.save(graduate);
        UUID id = graduateSaved.getId();
        repository.deleteById(id);

        Optional<Graduate> test = repository.findById(id);
        Assertions.assertTrue(test.isEmpty());
    }
}
