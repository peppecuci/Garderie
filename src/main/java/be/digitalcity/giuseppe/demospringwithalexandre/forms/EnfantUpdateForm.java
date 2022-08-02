package be.digitalcity.giuseppe.demospringwithalexandre.forms;

import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.TuteurDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class EnfantUpdateForm {

    private String firstName;
    private String lastName;
    private LocalDate dateDeNaissance;
    private boolean propre;
    private List<String> allergies;
    private Set<Long> tuteursId;

//
//    public Enfant toEntity(){
//
//        Enfant entity =  new Enfant();
//
//        entity.setFirstName(firstName);
//        entity.setLastName(lastName);
//        entity.setDateDeNaissance(dateDeNaissance);
//        entity.setPropre(propre);
//        entity.setAllergies(allergies);
//
//        return entity;
//
//    }

}
