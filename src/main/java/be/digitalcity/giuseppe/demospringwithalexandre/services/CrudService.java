package be.digitalcity.giuseppe.demospringwithalexandre.services;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CrudService<T, TID, TIFORM, TUFORM> {

    //CREATE
    T create(TIFORM toInsert);

    //UPDATE
    T  update(TID id, TUFORM toUpdate);

    //READ
    T getOne(TID id);
    List<T> getAll();

    //DELETE
    T delete(TID id);

    Set<T> getAllById(Collection<TID> ids);



}
