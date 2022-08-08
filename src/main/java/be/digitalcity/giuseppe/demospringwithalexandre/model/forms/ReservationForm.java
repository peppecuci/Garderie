package be.digitalcity.giuseppe.demospringwithalexandre.model.forms;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReservationForm {

    @NotNull
    @Future
    private LocalDateTime checkInHour;

    @NotNull
    @Future
    private LocalDateTime checkOutHour;

    private boolean isCancelled;

    private String reason;

}
