package be.digitalcity.giuseppe.demospringwithalexandre.services.impl;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.EnfantRepository;
import be.digitalcity.giuseppe.demospringwithalexandre.services.EnfantService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Primary
public class EnfantServiceImpl implements EnfantService {

    private final EnfantRepository repository;

    public EnfantServiceImpl(EnfantRepository repository) {
        this.repository = repository;
    }


    @Override
    public Enfant create(Enfant toInsert) {

        if(toInsert == null)
            throw new IllegalArgumentException("inserted child cannot be null");

        toInsert.setId(0L);

        return repository.save(toInsert);

    }

    @Override
    public Enfant update(Long id, Enfant toUpdate) {

        if(toUpdate == null || id == null)
            throw new IllegalArgumentException("params cannot be null");

        if(!repository.existsById(id))
            throw new EntityNotFoundException();

        toUpdate.setId(id);

        return repository.save(toUpdate);

    }

    @Override
    public Enfant getOne(long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Enfant> getAll() {
        return repository.findAll();
    }

    @Override
    public Enfant delete(Long id) {
        Enfant enfant = getOne(id);
        repository.delete(enfant);
        return enfant;
    }
}
