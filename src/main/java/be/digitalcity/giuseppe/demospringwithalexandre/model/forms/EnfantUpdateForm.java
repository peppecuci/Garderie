package be.digitalcity.giuseppe.demospringwithalexandre.model.forms;

import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.TuteurDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class EnfantUpdateForm {

    @NotBlank
    @Size(max = 255)
    private String firstName;

    @NotBlank
    @Size(max = 255)
    private String lastName;

    @PastOrPresent
    private LocalDate dateDeNaissance;

    @NotNull
    private boolean propre = true;

    @Size(max = 10)
    @NotNull
    private List<String> allergies;

    @NotNull
    private Set<Long> tuteursId = new HashSet<>();

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
