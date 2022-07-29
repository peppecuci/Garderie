package be.digitalcity.giuseppe.demospringwithalexandre.mapper;

import be.digitalcity.giuseppe.demospringwithalexandre.forms.EnfantInsertForm;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.EnfantDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import org.springframework.stereotype.Component;

@Component
public class EnfantMapper {

    public EnfantDTO toDto(Enfant entity){

        if(entity == null)
            return null;

        return EnfantDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .dateDeNaissance(entity.getDateDeNaissance())
                .allergies((entity.getAllergies()))
                .propre(entity.isPropre() ? "propre" : "non-propre" )
                .build();
    }

    public Enfant toEntity(EnfantInsertForm form){

        if(form == null)
            return null;

        Enfant enfant = new Enfant();
        enfant.setFirstName(form.getFirstName());
        enfant.setLastName(form.getLastName());
        enfant.setDateDeNaissance(form.getDateDeNaissance());
        enfant.setPropre(form.isPropre());

        return enfant;
    }

}
