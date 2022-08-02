package be.digitalcity.giuseppe.demospringwithalexandre.services.impl;


import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.TuteurRepository;
import be.digitalcity.giuseppe.demospringwithalexandre.services.TuteurService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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
            throw new EntityNotFoundException();

        toUpdate.setId(id);

        return repository.save(toUpdate);

    }

    @Override
    public Tuteur getOne(long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Tuteur> getAll() {
        return repository.findAll();
    }

    @Override
    public Tuteur delete(Long id) {
        Tuteur tuteur = getOne(id);
        repository.delete(tuteur);
        return tuteur;
    }
}
