package be.digitalcity.giuseppe.demospringwithalexandre.exceptions;

public class TuteurNotExistingException extends RuntimeException {

    public TuteurNotExistingException() {
        super("One or various 'Tuteurs' are not existing");
    }

}
