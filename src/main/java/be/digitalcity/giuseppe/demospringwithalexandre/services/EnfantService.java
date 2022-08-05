package be.digitalcity.giuseppe.demospringwithalexandre.services;

import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.EnfantDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.EnfantInsertForm;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.EnfantUpdateForm;

import java.util.Collection;
import java.util.List;

public interface EnfantService extends  CrudService<EnfantDTO, Long, EnfantInsertForm, EnfantUpdateForm> {

    EnfantDTO patchTuteurs(long id, Collection<Long> tuteursId);

}
