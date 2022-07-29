package be.digitalcity.giuseppe.demospringwithalexandre.services.impl;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.services.EnfantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnfantServiceMock implements EnfantService {

    @Override
    public Enfant save(Enfant T) {
        return null;
    }

    @Override
    public Enfant getOne(long id) {
        return new Enfant();
    }

    @Override
    public List<Enfant> getAll() {
        return List.of(new Enfant());
    }

    @Override
    public Enfant delete(Long id) {
        if(id == 5){
            return null;
        }

        return new Enfant();
    }
}
