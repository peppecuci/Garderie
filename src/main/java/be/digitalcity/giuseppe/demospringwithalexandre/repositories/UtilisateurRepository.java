package be.digitalcity.giuseppe.demospringwithalexandre.repositories;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Utilisateur;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {

    public Optional<Utilisateur> findByUsername(String username);

}
