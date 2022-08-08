package be.digitalcity.giuseppe.demospringwithalexandre.repositories;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresseRepository extends JpaRepository<Adresse, Long>, CustomAdresseRepository {
}
