package be.digitalcity.giuseppe.demospringwithalexandre.model.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
public class Adresse {

    @Id
    private int id;

    @Positive
    @NotNull
    private int nbr;

    @NotBlank
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
