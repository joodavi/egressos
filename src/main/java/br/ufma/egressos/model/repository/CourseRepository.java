package br.ufma.egressos.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufma.egressos.model.Course;

public interface CourseRepository extends JpaRepository<Course, UUID>{
    
}
