package br.ufma.egressos.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="graduate_course")
public class GraduateCourse {
    @EmbeddedId
    GraduateCoursePK id;

    @Column(name = "initial_date")
    LocalDate initialDate;

    @Column(name = "finish_date")
    LocalDate finishDate;

    // relationships

    @ManyToOne
    @MapsId("course_id")
    @JoinColumn(name = "course_id")
    Course course;

    @ManyToOne
    @MapsId("graduate_id")
    @JoinColumn(name = "graduate_id")
    Graduate graduate;
}
