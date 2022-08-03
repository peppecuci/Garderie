package be.digitalcity.giuseppe.demospringwithalexandre.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class EnfantDTO {

    private long id;
    private String firstName;
    private String lastName;
    private LocalDate dateDeNaissance;
    private String propre;
    private List<String> allergies;
    private Set<TuteurDTO> tuteursId;

}
