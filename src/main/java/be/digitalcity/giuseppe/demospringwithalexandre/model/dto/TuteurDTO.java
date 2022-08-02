package be.digitalcity.giuseppe.demospringwithalexandre.model.dto;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class TuteurDTO {

    private long id;
    private Set<Enfant> enfants;
}
