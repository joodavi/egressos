package br.ufma.egressos.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name="testimony")
public class Testimony {
    @Id
    @Column(name = "id_testimony")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "text")
    private String text;

    @Column(name = "register_date")
    private LocalDate date;

    // relationships

    @ManyToOne
    @JoinColumn(name = "graduate_id")
    private Graduate graduate;
}
