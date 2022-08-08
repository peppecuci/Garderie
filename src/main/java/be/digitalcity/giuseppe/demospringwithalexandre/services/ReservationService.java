package be.digitalcity.giuseppe.demospringwithalexandre.services;

import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.ReservationDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Reservation;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.ReservationForm;

import java.util.List;

public interface ReservationService{

    ReservationDTO create(ReservationForm toInsert);

    ReservationDTO getOne(Long id);

    List<ReservationDTO> getAll();

    ReservationDTO cancelById(Long id);

}
