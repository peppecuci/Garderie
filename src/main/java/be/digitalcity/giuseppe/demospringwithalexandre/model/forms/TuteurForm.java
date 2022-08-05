package be.digitalcity.giuseppe.demospringwithalexandre.model.forms;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Adresse;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class TuteurForm {

    @NotBlank
    @Size(max = 255)
    private String firstName;

    @NotBlank
    @Size(max = 255)
    private String lastName;

    @NotNull
    @Pattern(regexp = "[0-9]{3,4}\\/[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}")
    private String numTel;


    private Adresse adresse;

}
