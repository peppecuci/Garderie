package be.digitalcity.giuseppe.demospringwithalexandre.model.dto;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class TuteurDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String numTel;
    private String adresse;
    private Set<EnfantDTO> enfants;

    @Data
    @Builder
    public static class EnfantDTO {
        private Long id;
        private String nom;
        private String prenom;
        private LocalDate dateNaiss;
        private String proprete;
        private List<String> allergies;


        public static EnfantDTO fromEntity(Enfant entity){
            if( entity == null )
                return null;

            return EnfantDTO.builder()
                    .id( entity.getId() )
                    .prenom( entity.getFirstName() )
                    .nom( entity.getLastName() )
                    .dateNaiss( entity.getDateDeNaissance() )
                    .allergies( entity.getAllergies() )
                    .proprete( entity.isPropre() ? "propre" : "non-propre" )
                    .build();
        }



    }

}
