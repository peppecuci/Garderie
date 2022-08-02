package be.digitalcity.giuseppe.demospringwithalexandre.mapper;

import be.digitalcity.giuseppe.demospringwithalexandre.forms.EnfantInsertForm;
import be.digitalcity.giuseppe.demospringwithalexandre.forms.TuteurInsertForm;
import be.digitalcity.giuseppe.demospringwithalexandre.forms.TuteurUpdateForm;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.TuteurDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import org.springframework.stereotype.Component;

@Component
public class TuteurMapper {

    public TuteurDTO toDto(Tuteur entity){

        if(entity == null)
            return null;

        return TuteurDTO.builder()
                .id(entity.getId())
                .enfants(entity.getEnfants())
                .build();
    }

    //Cette methode definie le FORM à utiliser quand on insert un Tuteur dans la DB
    public Tuteur toEntity(TuteurInsertForm form){

        if(form == null)
            return null;

        Tuteur tuteur = new Tuteur();
        tuteur.setNumTel(form.getNumTel());
        tuteur.setAdresse(form.getAdresse());
        tuteur.setEnfants(form.getEnfants());

        return tuteur;
    }

    //Methode qui definie le FORM à utiliser quand on update un Tuteur dans la DB
    public Tuteur toEntity(TuteurUpdateForm form){

        if(form == null)
            return null;

        Tuteur tuteur = new Tuteur();
        tuteur.setFirstName(form.getFirstName());
        tuteur.setLastName(form.getLastName());
        tuteur.setNumTel(form.getNumTel());
        tuteur.setAdresse(form.getAdresse());

        return tuteur;

    }

}
