package be.digitalcity.giuseppe.demospringwithalexandre.services.impl;


import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.ElementNotFoundException;
import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.TuteurNotDeletableException;
import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.TuteurNotExistingException;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.TuteurRepository;
import be.digitalcity.giuseppe.demospringwithalexandre.services.TuteurService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TuteurServiceImpl implements TuteurService {

    private final TuteurRepository repository;

    public TuteurServiceImpl(TuteurRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tuteur create(Tuteur toInsert) {
        if( toInsert == null )
            throw new IllegalArgumentException("parameter 0 cannot be null");

        toInsert.setId(null);
        return repository.save(toInsert);
    }

    @Override
    public Tuteur update(Long id, Tuteur toUpdate) {
        if( toUpdate == null || id == null )
            throw new IllegalArgumentException("parameters cannot be null");

        if( !repository.existsById(id) )
            throw new ElementNotFoundException(Tuteur.class, id);

        toUpdate.setId(id);
        return repository.save( toUpdate );
    }

    @Override
    public Tuteur getOne(Long id) {
        if( id == null )
            throw new IllegalArgumentException("id cannot be null");

        return repository.findById(id)
                .orElseThrow( () -> new ElementNotFoundException(Tuteur.class, id) );
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

        try{
            repository.delete(tuteur);
        }
        catch (DataIntegrityViolationException ex){
            throw new TuteurNotDeletableException();
        }

        return tuteur;

    }


    @Override
    public Set<Tuteur> getAllById(Collection<Long> ids){

        List<Tuteur> listToCompare = repository.findAllById(ids);


        if( ids.size() == listToCompare.size() )
            return new HashSet<>( listToCompare );
        else
            throw new TuteurNotExistingException();
    }

    @Override
    public Enfant updateTuteurs(Long id, Set<Tuteur> tuteurs) {
        return null;
    }
}
