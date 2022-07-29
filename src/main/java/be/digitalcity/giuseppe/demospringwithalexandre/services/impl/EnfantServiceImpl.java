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
    public Enfant save(Enfant enfant) {

        if(enfant == null)
            throw  new IllegalArgumentException("Enfant ne devrait pas etre null");
        return repository.save(enfant);

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
