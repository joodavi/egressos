package br.ufma.egressos.model;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.egressos.model.repository.RoleRepository;
import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class RoleTest {
    @Autowired
    RoleRepository repository;

    @Test
    public void shouldSaveRole() {
        Role role = Role.builder().name("Role 1").description("Description 1").build();

        Role roleSaved = repository.save(role);

        Assertions.assertNotNull(roleSaved);
        Assertions.assertEquals(role.getName(), roleSaved.getName());
        Assertions.assertEquals(role.getDescription(), roleSaved.getDescription());
    }

    @Test
    public void shouldFindRoleByName() {
        Role role = Role.builder().name("Role 1").description("Description 1").build();

        Role roleSaved = repository.save(role);

        Role roleResult = repository.findByName(roleSaved.getName());

        Assertions.assertNotNull(roleResult);
        Assertions.assertEquals(roleSaved.getName(), roleResult.getName());
    }

    @Test
    public void shouldUpdateRole() {
        Role role = Role.builder().name("Role 1").description("Description 1").build();

        Role roleSaved = repository.save(role);

        roleSaved.setName("Role 2");

        Assertions.assertNotNull(roleSaved);
        Assertions.assertFalse(roleSaved.getName().equals("Role 1"));
        Assertions.assertEquals(roleSaved.getName(), "Role 2");
    }

    @Test
    public void shouldDeleteRole() {
        Role role = Role.builder().name("Role 1").description("Description 1").build();

        Role roleSaved = repository.save(role);
        UUID id = roleSaved.getId();
        repository.deleteById(id);

        Optional<Role> test = repository.findById(id);
        Assertions.assertTrue(test.isEmpty());
    }
}
