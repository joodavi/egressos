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
@Table(name="current_occupation")
public class CurrentOccupation {
    @Id
    @Column(name = "id_currOccupation")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "company")
    private String company;

    @Column(name = "description")
    private String description;

    @Column(name = "register_date")
    private LocalDate date;

    // relationships

    @ManyToOne
    @JoinColumn(name = "graduate_id")
    private Graduate graduate;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "salaryRange_id")
    private SalaryRange salaryRange;
}
