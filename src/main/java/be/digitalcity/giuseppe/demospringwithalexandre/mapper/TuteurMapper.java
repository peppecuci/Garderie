package be.digitalcity.giuseppe.demospringwithalexandre.mapper;

import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.TuteurForm;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.TuteurDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TuteurMapper {


    public TuteurDTO toDto(Tuteur entity){

        if(entity == null)
            return null;

        Set<TuteurDTO.EnfantDTO> enfants = new HashSet<>();
        if(entity.getEnfants() != null)
            entity.getEnfants().stream().map(TuteurDTO.EnfantDTO::fromEntity).forEach(enfants::add);

        return TuteurDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .numTel(entity.getNumTel())
                .adresse(entity.getAdresse().toString())
                .enfants(enfants)
                .build();
    }

    //Cette methode definie le FORM à utiliser quand on insert un Tuteur dans la DB
    public Tuteur toEntity(TuteurForm form){

        if(form == null)
            return null;

        Tuteur entity = new Tuteur();
        entity.setFirstName(form.getFirstName());
        entity.setLastName(form.getLastName());
        entity.setNumTel(form.getNumTel());
        entity.setAdresse(form.getAdresse());

        return entity;
    }



}
