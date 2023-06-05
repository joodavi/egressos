package br.ufma.egressos.model;

import java.util.UUID;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name="contact")
public class Contact {
    @Id
    @Column(name = "id_contact")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "urlLogo")
    private String urlLogo;

    // relationships

    @ManyToMany(mappedBy = "contacts")
    private List<Graduate> graduates;
}
