package be.digitalcity.giuseppe.demospringwithalexandre.services.impl;


import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.ElementNotFoundException;
import be.digitalcity.giuseppe.demospringwithalexandre.mapper.TuteurMapper;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.TuteurDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Personne;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.TuteurForm;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.AdresseRepository;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.EnfantRepository;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.TuteurRepository;
import be.digitalcity.giuseppe.demospringwithalexandre.services.TuteurService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TuteurServiceImpl implements TuteurService {

    private final TuteurRepository repository;
    private final TuteurMapper mapper;
    private final AdresseRepository adresseRepository;

    public TuteurServiceImpl(TuteurRepository repository, TuteurMapper mapper, AdresseRepository adresseRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.adresseRepository = adresseRepository;
    }

    @Override
    public TuteurDTO create(TuteurForm toInsert) {
        Tuteur tuteur = mapper.toEntity(toInsert);

        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();
        adresseRepository.findAll( Example.of( tuteur.getAdresse(), matcher ) ).stream()
                .findFirst()
                .ifPresentOrElse(
                        tuteur::setAdresse,
                        () -> tuteur.setAdresse(adresseRepository.save(tuteur.getAdresse()))
                );

        return mapper.toDto( repository.save( tuteur ) );
    }

    @Override
    public TuteurDTO update(Long id, TuteurForm toUpdate) {
        Tuteur tuteur = mapper.toEntity(toUpdate);
        tuteur.setId(id);
        return mapper.toDto( repository.save(tuteur) );
    }

    @Override
    public TuteurDTO getOne(Long id) {
        Tuteur tuteur = repository.findById(id)
                .orElseThrow( () -> new ElementNotFoundException(Tuteur.class, id));

        return mapper.toDto( tuteur );
    }

    @Override
    public List<TuteurDTO> getAll() {
        return repository.findAll().stream()
                .map( mapper::toDto )
                .toList();
    }
    @Override
    public TuteurDTO delete(Long id) {
        Tuteur tuteur = repository.findById(id)
                .orElseThrow( () -> new ElementNotFoundException(Tuteur.class, id));

        // Choix 1
//        if( tuteur.getEnfants() != null && !tuteur.getEnfants().isEmpty() )
//            throw new ReferencedSuppresionException(
//                    tuteur.getEnfants().stream()
//                            .map(Personne::getId)
//                            .collect(Collectors.toSet())
//            );

        // Choix 2
        try {
            repository.delete(tuteur);
        }
        catch (DataIntegrityViolationException ex){
            throw new ElementNotFoundException(
                    ex.getMessage(),
                    Enfant.class,
                    tuteur.getEnfants().stream()
                            .map(Personne::getId)
                            .collect(Collectors.toSet())
            );
        }
        tuteur.setId(null);
        return mapper.toDto( tuteur );
    }

    @Override
    public Set<TuteurDTO> getAllById(Collection<Long> ids) {
        return repository.findAllById(ids).stream()
                .map( mapper::toDto )
                .collect(Collectors.toSet());
    }

    @Override
    public List<TuteurDTO> getAllFromVilleWithChild(String ville) {



//        return repository.findByAdresse_VilleIsAndEnfantsNotEmpty(ville).stream()
        return repository.findFromVille(ville).stream()
                .map(mapper::toDto)
                .toList();
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
