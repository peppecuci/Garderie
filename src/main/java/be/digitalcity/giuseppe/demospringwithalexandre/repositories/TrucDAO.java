package be.digitalcity.giuseppe.demospringwithalexandre.repositories;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TrucDAO {

    @PersistenceContext
    private EntityManager manager;



    public void machin(){

        //On peut manipuler les entites avec le manager, par exemple creer plusieur Query un peu plus complexes

    }


}
