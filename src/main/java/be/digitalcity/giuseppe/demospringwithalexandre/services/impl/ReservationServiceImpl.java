package be.digitalcity.giuseppe.demospringwithalexandre.services.impl;

import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.ElementNotFoundException;
import be.digitalcity.giuseppe.demospringwithalexandre.mapper.ReservationMapper;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.ReservationDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Reservation;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.ReservationForm;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.ReservationRepository;
import be.digitalcity.giuseppe.demospringwithalexandre.services.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationMapper mapper;
    private final ReservationRepository repository;

    public ReservationServiceImpl(ReservationMapper mapper, ReservationRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public ReservationDTO create(ReservationForm toInsert) {

        Reservation reservation = mapper.toEntity(toInsert);

        return mapper.toDto( repository.save( reservation ));

    }

    @Override
    public ReservationDTO getOne(Long id) {
        Reservation reservation = repository.findById(id)
                .orElseThrow( () -> new ElementNotFoundException(Reservation.class, id));

        return mapper.toDto( reservation );
    }

    @Override
    public List<ReservationDTO> getAll() {
        return repository.findAll().stream()
                .map( mapper::toDto )
                .toList();
    }

    @Override
    public ReservationDTO cancelById(Long id) {

        ReservationDTO reservation = getOne(id);
        reservation.setCancelled(true);

        return reservation;

    }

}
