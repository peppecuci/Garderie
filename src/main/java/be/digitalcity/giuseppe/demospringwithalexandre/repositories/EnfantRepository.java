package be.digitalcity.giuseppe.demospringwithalexandre.repositories;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnfantRepository extends JpaRepository<Enfant, Long> {


    List<Enfant> findByAllergiesContaining(String allergie);

    List<Enfant> findByFirstNameAndLastName(String prenom, String nom);

}
