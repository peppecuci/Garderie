package be.digitalcity.giuseppe.demospringwithalexandre.repositories;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import org.hibernate.cfg.JPAIndexHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TuteurRepository extends JpaRepository<Tuteur, Long> {

    List<Tuteur> findByAdresse_CityIsAndEnfantsNotEmpty(String ville);

    @Query("SELECT t FROM Tuteur t WHERE t.adresse.city = ?1 AND (t.enfants.size > 0)")
    List<Tuteur> findFromVille(String ville);

}
