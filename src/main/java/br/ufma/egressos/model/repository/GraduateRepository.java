package br.ufma.egressos.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufma.egressos.model.Graduate;

public interface GraduateRepository extends JpaRepository<Graduate, UUID>{
    Graduate findByName(String name);
    Graduate findByEmail(String email);
    Graduate findByCpf(String cpf);
}
