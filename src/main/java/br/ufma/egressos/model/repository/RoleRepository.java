package br.ufma.egressos.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufma.egressos.model.Role;

public interface RoleRepository extends JpaRepository<Role, UUID>{
    Role findByName(String name);
}