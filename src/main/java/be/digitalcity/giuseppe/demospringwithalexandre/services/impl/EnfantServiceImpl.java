package be.digitalcity.giuseppe.demospringwithalexandre.services.impl;

import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.ElementNotFoundException;
import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.TuteurNotExistingException;
import be.digitalcity.giuseppe.demospringwithalexandre.mapper.EnfantMapper;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.EnfantDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.EnfantInsertForm;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.EnfantUpdateForm;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.EnfantRepository;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.TuteurRepository;
import be.digitalcity.giuseppe.demospringwithalexandre.services.EnfantService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Primary
public class EnfantServiceImpl implements EnfantService {

    private final EnfantRepository repository;
    private final TuteurRepository tuteurRepository;
    private final EnfantMapper mapper;

    public EnfantServiceImpl(EnfantRepository repository, TuteurRepository tuteurRepository, EnfantMapper mapper) {
        this.repository = repository;
        this.tuteurRepository = tuteurRepository;
        this.mapper = mapper;
    }

    @Override
    public EnfantDTO create(EnfantInsertForm toInsert) {

        if (toInsert == null)
            throw new IllegalArgumentException("inserted child cannot be null");

        return mapper.toDto(repository.save(mapper.toEntity(toInsert)));

    }

    @Override
    public EnfantDTO update(Long id, EnfantUpdateForm toUpdate) {

        if (toUpdate == null || id == null)
            throw new IllegalArgumentException("params cannot be null");

        if (!repository.existsById(id))
            throw new ElementNotFoundException(Enfant.class, id);

        Enfant enfant = mapper.toEntity(toUpdate);
        List<Tuteur> tuteurs = tuteurRepository.findAllById(toUpdate.getTuteursId());
        enfant.setTuteurs(new HashSet<>(tuteurs));
        enfant.setId(id);

        return mapper.toDto(repository.save(enfant));

    }


    @Override
    public EnfantDTO getOne(Long id) {
        if (id == null)
            throw new IllegalArgumentException("id cannot be null");

        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ElementNotFoundException(Enfant.class, id));

    }

    @Override
    public List<EnfantDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public EnfantDTO delete(Long id) {

        Enfant enfant = repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(Enfant.class, id));

        repository.delete(enfant);
        enfant.setId(null);

        return mapper.toDto(enfant);
    }

    @Override
    public Set<EnfantDTO> getAllById(Collection<Long> ids) {

        return repository.findAllById(ids).stream()
                .map(mapper::toDto).collect(Collectors.toSet());

    }

    public Enfant updateTuteurs(Long id, Set<Tuteur> tuteurs) {

        Enfant enfant = repository.findById(id)
                .orElseThrow();
        enfant.setTuteurs(tuteurs);
        return enfant;

    }


    //METHODE POUR MODIFIER LES TUTEURS D'UN ENFANT
    @Override
    public EnfantDTO patchTuteurs(long id, Collection<Long> tuteursId) {

        Enfant enfant = repository.findById(id).orElseThrow(() -> new ElementNotFoundException(Enfant.class, id));

        List<Tuteur> tuteurs = tuteurRepository.findAllById(tuteursId);

        if (tuteurs.size() < tuteursId.size()) {
            List<Long> found = tuteurs.stream()
                    .map(Tuteur::getId)
                    .toList();
            List<Long> notFound = tuteursId.stream()
                    .filter(ident -> !found.contains(ident))
                    .toList();

            throw new TuteurNotExistingException();

        }

        enfant.setTuteurs(new HashSet<>(tuteurs));
        return mapper.toDto(repository.save(enfant));

    }
}