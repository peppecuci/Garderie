package be.digitalcity.giuseppe.demospringwithalexandre.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nbr;
    private String street;
    private int zip;
    private String city;

//    @OneToMany(mappedBy = "adresse") //dans le mappedBy faut mettre le nom de la variable associé qui se trouve dans la classe Tuteur, "voir private Adresse adresse;"
//    private Set<Tuteur> tuteurs = new LinkedHashSet<>();

    @Override
    public String toString() {
        return nbr + " " + " " + street + ", " + zip + " " + city;
    }


    //TODO gerer l'entité adresse avec ses relations avec les autres entités


}
