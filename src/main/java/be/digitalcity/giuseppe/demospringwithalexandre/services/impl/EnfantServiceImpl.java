package be.digitalcity.giuseppe.demospringwithalexandre.services.impl;

import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.ElementNotFoundException;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.EnfantRepository;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.TuteurRepository;
import be.digitalcity.giuseppe.demospringwithalexandre.services.EnfantService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Primary
public class EnfantServiceImpl implements EnfantService {

    private final EnfantRepository repository;
    private final TuteurRepository tuteurRepository;

    public EnfantServiceImpl(EnfantRepository repository, TuteurRepository tuteurRepository) {
        this.repository = repository;
        this.tuteurRepository = tuteurRepository;
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
            throw new ElementNotFoundException(Enfant.class, id);

        toUpdate.setId(id);

        return repository.save(toUpdate);

    }


    @Override
    public Enfant getOne(Long id) {
        if( id == null )
            throw new IllegalArgumentException("id cannot be null");

        return repository.findById(id)
                .orElseThrow( () -> new ElementNotFoundException(Enfant.class, id) );
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

    @Override
    public Set<Enfant> getAllById(Collection<Long> ids) {
        return new HashSet<>(repository.findAllById(ids));
    }

    public Enfant updateTuteurs(Long id, Set<Tuteur> tuteurs){

        Enfant enfant = repository.findById(id)
                .orElseThrow();
        enfant.setTuteurs(tuteurs);
        return enfant;

    }

    @Override
    public Enfant patchTuteurs(long id, Collection<Long> tuteursId) {
        return null;
    }
}
