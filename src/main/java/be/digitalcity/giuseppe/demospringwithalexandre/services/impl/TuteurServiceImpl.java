package be.digitalcity.giuseppe.demospringwithalexandre.services.impl;


import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.ElementNotFoundException;
import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.TuteurNotDeletableException;
import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.TuteurNotExistingException;
import be.digitalcity.giuseppe.demospringwithalexandre.mapper.TuteurMapper;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.TuteurDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Personne;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.TuteurForm;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.EnfantRepository;
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
    private final TuteurMapper mapper;
    private final EnfantRepository enfantRepository;

    public TuteurServiceImpl(TuteurRepository repository, TuteurMapper mapper, EnfantRepository enfantRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.enfantRepository = enfantRepository;
    }

    @Override
    public TuteurDTO create(TuteurForm toInsert) {
        if( toInsert == null )
            throw new IllegalArgumentException("parameter 0 cannot be null");

        return mapper.toDto(repository.save(mapper.toEntity(toInsert)));

    }

    @Override
    public TuteurDTO update(Long id, TuteurForm toUpdate) {
        if( toUpdate == null || id == null )
            throw new IllegalArgumentException("parameters cannot be null");

        if( !repository.existsById(id) )
            throw new ElementNotFoundException(Tuteur.class, id);

        Tuteur tuteur = mapper.toEntity(toUpdate);
        return mapper.toDto(repository.save( tuteur ));

    }

    @Override
    public TuteurDTO getOne(Long id) {
        if( id == null )
            throw new IllegalArgumentException("id cannot be null");

        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow( () -> new ElementNotFoundException(Tuteur.class, id) );
    }

    @Override
    public List<TuteurDTO> getAll() {

        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();

    }

    @Override
    public TuteurDTO delete(Long id) {

        Tuteur tuteur = repository.findById(id)
                .orElseThrow( () -> new ElementNotFoundException(Tuteur.class, id));

        try{

            repository.delete(tuteur);
            tuteur.setId(null);

        }

        catch (DataIntegrityViolationException ex){

            throw  new TuteurNotDeletableException(
//                    tuteur.getEnfants().stream()
//                            .map(Personne::getId)
//                            .collect(Collectors.toCollection())
            );

        }

        return mapper.toDto(tuteur);

    }


    @Override
    public Set<TuteurDTO> getAllById(Collection<Long> ids){

        List<Tuteur> listToCompare = repository.findAllById(ids);

        if( ids.size() == listToCompare.size() )
            return new HashSet<>( listToCompare ).stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toSet());
        else
            throw new TuteurNotExistingException();
    }

//    @Override
//    public TuteurDTO updateTuteur(Long id, TuteurForm form) {
//
//        Tuteur tuteur = repository.findById(id).orElseThrow( () -> new ElementNotFoundException(Tuteur.class, id) );
//
//        tuteur.
//
//    }
}
