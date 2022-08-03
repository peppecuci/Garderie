package be.digitalcity.giuseppe.demospringwithalexandre.mapper;

import be.digitalcity.giuseppe.demospringwithalexandre.forms.EnfantInsertForm;
import be.digitalcity.giuseppe.demospringwithalexandre.forms.EnfantUpdateForm;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.EnfantDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.TuteurDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EnfantMapper {

    private final TuteurMapper tuteurMapper;

    public EnfantMapper(TuteurMapper tuteurMapper) {
        this.tuteurMapper = tuteurMapper;
    }

    @Transactional
    public EnfantDTO toDto(Enfant entity){

        if(entity == null)
            return null;

        Set<TuteurDTO> dtos = entity.getTuteurs().stream()
                .map(tuteurMapper::toDto)
                .collect(Collectors.toSet());

        return EnfantDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .dateDeNaissance(entity.getDateDeNaissance())
                .allergies((entity.getAllergies()))
                .propre(entity.isPropre() ? "propre" : "non-propre" )
                .tuteursId(dtos)
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

    //Il est à remarquer qu'on ne mappe pas l'id ou les tuteurs
    public Enfant toEntity(EnfantUpdateForm form){

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
