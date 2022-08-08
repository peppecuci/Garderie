package be.digitalcity.giuseppe.demospringwithalexandre.model.forms;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Adresse;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class AdresseForm {

    @Positive
    private int nbr;
    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @Positive
    private int zip;

    public Adresse toEntity(){

        Adresse entity = new Adresse();

        entity.setCity(this.city);
        entity.setStreet(this.street);
        entity.setZip(this.zip);
        entity.setNbr(this.nbr);

        return entity;

    }
}
