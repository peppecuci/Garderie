package be.digitalcity.giuseppe.demospringwithalexandre.repositories;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Adresse;
import lombok.NonNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class CustomAdresseRepositoryImpl implements CustomAdresseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean exists(@NonNull Adresse adresse) {

        String request = "SELECT (COUNT(a) > 0) " +
                " FROM Adresse a" +
                " WHERE " +
                "   a.city = ?1 AND " +
                "   a.zip = ?2 AND " +
                "   a.street = ?3 AND " +
                "   a.nbr = ?4 ";
        TypedQuery<Boolean> query = entityManager.createQuery(request, Boolean.class);
        query.setParameter(1, adresse.getCity());
        query.setParameter(2, adresse.getZip());
        query.setParameter(3, adresse.getStreet());
        query.setParameter(4, adresse.getNbr());

        return query.getSingleResult();
    }

}
