package br.ufma.egressos.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufma.egressos.model.GraduateCourse;

public interface GraduateCourseRepository extends JpaRepository<GraduateCourse, UUID>{ 
}
