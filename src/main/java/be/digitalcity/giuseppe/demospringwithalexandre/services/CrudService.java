package be.digitalcity.giuseppe.demospringwithalexandre.services;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;

import java.util.List;

public interface CrudService<T, TID> {

    //CREATE / UPDATE
    T save(T T);

    //READ
    Enfant getOne(long id);
    List<T> getAll();

    //DELETE
    T delete(TID id);

}
