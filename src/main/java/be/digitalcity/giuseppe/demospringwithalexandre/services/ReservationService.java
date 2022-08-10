package be.digitalcity.giuseppe.demospringwithalexandre.services;

import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.EnfantDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.ReservationDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Reservation;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.ReservationForm;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public interface ReservationService{

    ReservationDTO create(ReservationForm toInsert);

    ReservationDTO getOne(Long id);

    List<ReservationDTO> getAll();

    ReservationDTO cancelById(Long id);

    List<EnfantDTO> getAllEnfantByDate(LocalDateTime dateTime);

    List<ReservationDTO> getAllByCheckInHourAfter(LocalDateTime dateTime, Long id);

    public List<ReservationDTO> getAllRemainingReservationOfTheCurrentMonth(Month month);
}
