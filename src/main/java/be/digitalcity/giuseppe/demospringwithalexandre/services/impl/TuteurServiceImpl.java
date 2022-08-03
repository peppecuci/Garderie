package be.digitalcity.giuseppe.demospringwithalexandre.services.impl;


import be.digitalcity.giuseppe.demospringwithalexandre.ElementNotFoundException;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.TuteurRepository;
import be.digitalcity.giuseppe.demospringwithalexandre.services.TuteurService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Primary
public class TuteurServiceImpl implements TuteurService {

    private final TuteurRepository repository;

    public TuteurServiceImpl(TuteurRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tuteur create(Tuteur toInsert) {

        if(toInsert == null)
            throw new IllegalArgumentException("Tuteur cannot be null");

        toInsert.setId(0L);

        return repository.save(toInsert);
    }

    @Override
    public Tuteur update(Long id, Tuteur toUpdate) {

        if(toUpdate == null || id == null)
            throw new IllegalArgumentException("No paramether can be null");

        if(!repository.existsById(id))
            throw new ElementNotFoundException(Tuteur.class, id);

        toUpdate.setId(id);

        return repository.save(toUpdate);

    }

    @Override
    public Tuteur getOne(long id) {
        if(!repository.existsById(id))
            throw new ElementNotFoundException(Tuteur.class, id);

        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Tuteur> getAll() {
        return repository.findAll();
    }

    @Override
    public Tuteur delete(Long id) {

        if(!repository.existsById(id))
            throw new ElementNotFoundException(Tuteur.class, id);

        Tuteur tuteur = getOne(id);
        repository.delete(tuteur);
        return tuteur;

    }

    @Override
    public Set<Tuteur> getAllById(Collection<Long> ids) {

        return new HashSet<>(repository.findAllById(ids));

    }
}
