package be.digitalcity.giuseppe.demospringwithalexandre.model.dto;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class ReservationDTO {

    private Long id;
    private LocalDateTime checkInHour;
    private LocalDateTime checkOutHour;
    private boolean cancelled;
    private String reason;
    private EnfantDTO enfant;

}
