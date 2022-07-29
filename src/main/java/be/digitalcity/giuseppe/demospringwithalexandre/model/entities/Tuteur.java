package be.digitalcity.giuseppe.demospringwithalexandre.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tuteur extends Personne{

    @Column(nullable = false)
    private String numTel;

    @Column(nullable = false)
    private String adresse;

    @ManyToMany(mappedBy = "tuteurs")
    private Set<Enfant> enfants;
}
