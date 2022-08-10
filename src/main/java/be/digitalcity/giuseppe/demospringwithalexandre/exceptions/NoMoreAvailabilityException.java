package be.digitalcity.giuseppe.demospringwithalexandre.exceptions;

public class NoMoreAvailabilityException extends RuntimeException{



    public NoMoreAvailabilityException() {
        super("No more availability for this day");
    }
}
