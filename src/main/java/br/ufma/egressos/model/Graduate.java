package br.ufma.egressos.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name="graduate")
public class Graduate {
    @Id
    @Column(name = "id_graduate")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "resume")
    private String resume;

    @Column(name = "urlPhoto")
    private String urlPhoto;

    // relationships

    @OneToMany(mappedBy = "graduate")
    private List<CurrentOccupation> occupations;

    @OneToMany(mappedBy = "graduate")
    private List<Testimony> testimonies;

    @OneToMany(mappedBy = "graduate")
    private List<GraduateCourse> graduateCourses;

    @ManyToMany
    @JoinTable(
        name = "contact_graduate",
        joinColumns = @JoinColumn(name = "graduate_id"),
        inverseJoinColumns = @JoinColumn(name = "contact_id"))
        private List<Contact> contacts;
}
