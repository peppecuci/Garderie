package be.digitalcity.giuseppe.demospringwithalexandre.model.dto;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class TuteurDTO {

    private String firstName;
    private String lastName;
    private String numTel;
    private String adresse;
    private long id;
    private Set<EnfantDTO> enfants;

    @Data
    @Builder
    public static class EnfantDTO {
        private long id;
        private String firstName;
        private String lastName;
        private LocalDate dateDeNaissance;
        private String propre;
        private List<String> allergies;

        public static EnfantDTO fromEntity(Enfant entity){
            if(entity == null)
                return null;

            return EnfantDTO.builder()
                    .id(entity.getId())
                    .firstName(entity.getFirstName())
                    .lastName(entity.getLastName())
                    .dateDeNaissance(entity.getDateDeNaissance())
                    .allergies((entity.getAllergies()))
                    .propre(entity.isPropre() ? "propre" : "non-propre" )
                    .build();
        }



    }

}
