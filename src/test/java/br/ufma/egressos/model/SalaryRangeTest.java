package br.ufma.egressos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.egressos.model.repository.SalaryRangeRepository;
import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class SalaryRangeTest {
    @Autowired
    SalaryRangeRepository repository;

    @Test
    public void shouldSaveRange() {
        SalaryRange range = SalaryRange.builder().description("2000 - 3000").build();

        SalaryRange rangeSaved = repository.save(range);

        Assertions.assertNotNull(rangeSaved);
        Assertions.assertEquals(range.getDescription(), rangeSaved.getDescription());
    }

    @Test
    public void shouldSaveMultipleRange() {
        SalaryRange range1 = SalaryRange.builder().description("1000 - 2000").build();
        SalaryRange range2 = SalaryRange.builder().description("2000 - 3000").build();

        List<SalaryRange> ranges = new ArrayList<>();
        ranges.add(range1);
        ranges.add(range2);

        List<SalaryRange> test = new ArrayList<>();
        test.addAll(ranges);

        Assertions.assertNotNull(test);
        Assertions.assertEquals(ranges.size(), test.size());
    }

    @Test
    public void shouldFindByRange() {
        SalaryRange range = SalaryRange.builder().description("2000 - 3000").build();

        SalaryRange rangeSaved = repository.save(range);
        SalaryRange rangeResult = repository.findByDescription(rangeSaved.getDescription());

        Assertions.assertNotNull(rangeResult);
        Assertions.assertEquals(rangeSaved.getDescription(), rangeResult.getDescription());
    }

    @Test
    public void shouldUpdateRange() {
        SalaryRange range = SalaryRange.builder().description("2000 - 3000").build();

        SalaryRange rangeSaved = repository.save(range);

        rangeSaved.setDescription("1000 - 2000");

        Assertions.assertNotNull(rangeSaved);
        Assertions.assertFalse(rangeSaved.getDescription().equals("2000 - 3000"));
        Assertions.assertEquals(range.getDescription(), "1000 - 2000");
    }

    @Test
    public void shouldDeleteRange() {
        SalaryRange range = SalaryRange.builder().description("2000 - 3000").build();

        SalaryRange rangeSaved = repository.save(range);
        UUID id = rangeSaved.getId();
        repository.deleteById(id);

        Optional<SalaryRange> test = repository.findById(id);
        Assertions.assertTrue(test.isEmpty());
    }

}
