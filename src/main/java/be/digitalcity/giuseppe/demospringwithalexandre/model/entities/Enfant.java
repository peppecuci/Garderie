package be.digitalcity.giuseppe.demospringwithalexandre.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enfant extends Personne {

    private LocalDate dateDeNaissance;
    private boolean propre;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> allergies;

    @ManyToMany
    @JoinTable(name = "tutorat", joinColumns = @JoinColumn(name = "enfant°id"),
    inverseJoinColumns = @JoinColumn(name = "tuteur_id"))
    private Set<Tuteur> tuteurs = new HashSet<>();

    @OneToMany
    private List<Reservation> reservation;

}
