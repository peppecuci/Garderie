package be.digitalcity.giuseppe.demospringwithalexandre.model.forms;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Data

public class EnfantInsertForm {

    @NotBlank
    @Size(max = 255)
    private String firstName;

    @NotBlank
    @Size(max = 255)
    private String lastName;


    @PastOrPresent //(message = "Wesh, ne met pas une date future")
//    @NotNull
    private LocalDate dateDeNaissance;

    @NotNull
//    @AssertTrue ca verifie si c'est true -
    private boolean propre = true;

    @Size(max = 10)
    @NotNull
    //TODO Pas de blank => ne pas accepter un valeur ou il y a que des espaces ex: (____) ne pas accepté, (___a___) est accepté
    //TODO TRIM => va traduire le (___a___) en (a)
    private Set<String> allergies;


    @NotNull
    //TODO s'assurer que chaque ID mene à un Tuteur
    private Set<Long> tuteursId; // TODO remove clean

}
