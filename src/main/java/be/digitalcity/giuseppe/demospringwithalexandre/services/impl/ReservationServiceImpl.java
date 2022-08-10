package be.digitalcity.giuseppe.demospringwithalexandre.services.impl;

import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.ElementNotFoundException;
import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.FormValidationException;
import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.NoMoreAvailabilityException;
import be.digitalcity.giuseppe.demospringwithalexandre.mapper.EnfantMapper;
import be.digitalcity.giuseppe.demospringwithalexandre.mapper.ReservationMapper;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.EnfantDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.ReservationDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Reservation;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.ReservationForm;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.EnfantRepository;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.ReservationRepository;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.TuteurRepository;
import be.digitalcity.giuseppe.demospringwithalexandre.services.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationMapper mapper;
    private final ReservationRepository repository;
    private final EnfantRepository enfantRepository;
    private final EnfantMapper enfantMapper;

    public ReservationServiceImpl(ReservationMapper mapper, ReservationRepository repository, TuteurRepository tuteurRepository, EnfantRepository enfantRepository, EnfantMapper enfantMapper) {
        this.mapper = mapper;
        this.repository = repository;
        this.enfantRepository = enfantRepository;
        this.enfantMapper = enfantMapper;
    }

    @Override
    public ReservationDTO create(ReservationForm toInsert) {

        Reservation reservation = mapper.toEntity(toInsert);

        List<Reservation> counter = repository
                .getAllReservationByDate(
                        reservation.getCheckInHour().toLocalDate().atStartOfDay(),
                        reservation.getCheckOutHour().toLocalDate().atTime(23, 59)
                );

        if(counter.size() < reservation.getAvailability()) {
            Enfant enfant = enfantRepository.findById(toInsert.getEnfantId()).orElseThrow(() -> new FormValidationException(null));
            reservation.setEnfant(enfant);

            return mapper.toDto(repository.save(reservation));
        }
        else
            throw new NoMoreAvailabilityException();

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

    @Override
    public List<EnfantDTO> getAllEnfantByDate(LocalDateTime dateBegin){

        return repository.getAllEnfantByDate(dateBegin.toLocalDate().atStartOfDay(), dateBegin.toLocalDate().atTime(23,59))
                .stream().map(enfantMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public List<ReservationDTO> getAllByCheckInHourAfter(LocalDateTime dateTime, Long id){

        return repository.getAllByCheckInHourAfter(id).stream().map(mapper::toDto).collect(Collectors.toList());

    }

    @Override
    public List<ReservationDTO> getAllRemainingReservationOfTheCurrentMonth(Month month) {

        month = LocalDateTime.now().getMonth();
        return repository.getAllRemainingReservationOfTheCurrentMonth(month).stream().map(mapper::toDto).collect(Collectors.toList());

    }






}
