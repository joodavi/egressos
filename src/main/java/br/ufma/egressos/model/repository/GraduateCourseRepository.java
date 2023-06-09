package br.ufma.egressos.model.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufma.egressos.model.GraduateCourse;
import br.ufma.egressos.model.GraduateCoursePK;

public interface GraduateCourseRepository extends JpaRepository<GraduateCourse, UUID>{

    Optional<GraduateCourse> findById(GraduateCoursePK id); 
}
