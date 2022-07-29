package be.digitalcity.giuseppe.demospringwithalexandre.repositories;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnfantRepository extends JpaRepository<Enfant, Long> {
}
