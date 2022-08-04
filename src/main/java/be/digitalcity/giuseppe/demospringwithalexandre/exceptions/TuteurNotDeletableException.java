package be.digitalcity.giuseppe.demospringwithalexandre.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class TuteurNotDeletableException extends RuntimeException {

    public TuteurNotDeletableException() {
        super("It's not possible to delete a 'Tuteur' when is linked to an 'Enfant' ");
    }
}
