package be.digitalcity.giuseppe.demospringwithalexandre.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tuteur extends Personne{

    @Column(nullable = false)
    private String numTel;

    @ManyToOne
    @JoinColumn(name = "adresseId")
    private Adresse adresse;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tuteurs")
    private Set<Enfant> enfants;

    @OneToMany
    private List<Reservation> reservations;

}
