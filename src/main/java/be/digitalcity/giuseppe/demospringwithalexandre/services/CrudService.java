package be.digitalcity.giuseppe.demospringwithalexandre.services;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;

import java.util.List;

public interface CrudService<T, TID> {

    //CREATE
    T create(T toInsert);

    //UPDATE
    T update(TID id, T toUpdate);

    //READ
    T getOne(long id);
    List<T> getAll();

    //DELETE
    T delete(TID id);

}
