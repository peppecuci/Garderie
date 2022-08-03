package be.digitalcity.giuseppe.demospringwithalexandre.forms;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data

public class EnfantInsertForm {

    @NotBlank
    @Size(max = 50)
    private String firstName;
    private String lastName;
    @PastOrPresent
    private LocalDate dateDeNaissance;
    private boolean propre;
    private Set<Long> setTuteursId;

}
