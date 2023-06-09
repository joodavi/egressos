package br.ufma.egressos.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class GraduateCoursePK implements Serializable {
    @Column(name = "course_id")
    UUID course_id;

    @Column(name = "graduate_id")
    UUID graduate_id;
}
