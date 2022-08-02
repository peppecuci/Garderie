package be.digitalcity.giuseppe.demospringwithalexandre.forms;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import lombok.Data;

import java.util.Set;

@Data
public class TuteurInsertForm {

    private String numTel;
    private String adresse;
    private Set<Enfant> enfants;
}
