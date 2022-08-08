package be.digitalcity.giuseppe.demospringwithalexandre.model.dto;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Adresse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdresseDTO {

    private Long nbr;
    private String street;
    private String city;
    private int zip;

    public static AdresseDTO fromEntity(Adresse entity){
        if(entity == null)
            return null;

        return AdresseDTO.builder()
                .nbr(entity.getId())
                .city(entity.getCity())
                .street(entity.getStreet())
                .zip(entity.getZip())
                .build();
    }

}
