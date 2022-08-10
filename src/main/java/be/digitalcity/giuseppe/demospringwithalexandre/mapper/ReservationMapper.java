package be.digitalcity.giuseppe.demospringwithalexandre.mapper;

import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.ReservationDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Reservation;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.ReservationForm;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    private final EnfantMapper mapper;

    public ReservationMapper(EnfantMapper mapper) {
        this.mapper = mapper;
    }

    public ReservationDTO toDto(Reservation entity){

        if(entity == null)
            return null;


        return ReservationDTO.builder()
                .id(entity.getId())
                .checkInHour(entity.getCheckInHour())
                .checkOutHour(entity.getCheckOutHour())
                .cancelled(entity.isCancelled())
                .reason(entity.getReason())
                .enfant( mapper.toDto(entity.getEnfant()))
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
