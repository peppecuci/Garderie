package be.digitalcity.giuseppe.demospringwithalexandre.forms;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data

public class EnfantInsertForm {

    private long id;
    private String firstName;
    private String lastName;
    private LocalDate dateDeNaissance;
    private boolean propre;

}
