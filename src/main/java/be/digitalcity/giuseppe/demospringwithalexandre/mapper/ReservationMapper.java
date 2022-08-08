package be.digitalcity.giuseppe.demospringwithalexandre.mapper;

import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.ReservationDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Reservation;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.ReservationForm;

public class ReservationMapper {

    public ReservationDTO toDto(Reservation entity){

        if(entity == null)
            return null;

        return ReservationDTO.builder()
                .id(entity.getId())
                .checkInHour(entity.getCheckInHour())
                .checkOutHour(entity.getCheckOutHour())
                .cancelled(entity.isCancelled())
                .reason(entity.getReason())
                .build();
    }


    public Reservation toEntity(ReservationForm form){

        if(form == null)
            return null;

        Reservation entity = new Reservation();
        entity.setCheckInHour(form.getCheckInHour());
        entity.setCheckOutHour(form.getCheckOutHour());
        entity.setCancelled(form.isCancelled());
        entity.setReason(form.getReason());

        return entity;
    }

}
