package be.digitalcity.giuseppe.demospringwithalexandre.services;

import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.TuteurDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.TuteurForm;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface TuteurService extends CrudService<TuteurDTO, Long, TuteurForm, TuteurForm>{

    Set<TuteurDTO> getAllById(Collection<Long> ids);

    List<TuteurDTO> getAllFromVilleWithChild(String ville);

}
