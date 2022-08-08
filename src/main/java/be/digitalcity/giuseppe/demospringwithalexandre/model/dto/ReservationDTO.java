package be.digitalcity.giuseppe.demospringwithalexandre.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ReservationDTO {

    private Long id;
    private LocalDateTime checkInHour;
    private LocalDateTime checkOutHour;
    private boolean cancelled;
    private String reason;

}
