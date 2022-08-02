package be.digitalcity.giuseppe.demospringwithalexandre.forms;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import lombok.Data;

import java.util.Set;

@Data
public class TuteurUpdateForm {

    private String firstName;
    private String lastName;

    private String numTel;
    private String adresse;

    private Set<Long> enfantsId;

}
