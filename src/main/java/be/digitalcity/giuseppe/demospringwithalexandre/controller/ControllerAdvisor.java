package be.digitalcity.giuseppe.demospringwithalexandre.controller;

import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.ElementNotFoundException;
import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.FormValidationException;
import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.TuteurNotDeletableException;
import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.TuteurNotExistingException;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.ErrorDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {


    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleException(ElementNotFoundException ex, HttpServletRequest req){


        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorDTO.builder()
                                .message( ex.getMessage() )
                                .receivedAt( LocalDateTime.now() )
                                .status( 404 )
                                .method( HttpMethod.resolve(req.getMethod()) )
                                .path( req.getRequestURL().toString() )
                                .build());
    }

    @ExceptionHandler(TuteurNotDeletableException.class)
    public ResponseEntity<ErrorDTO> handleException(TuteurNotDeletableException ex, HttpServletRequest request){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorDTO.builder()
                                .message( ex.getMessage() )
                                .receivedAt( LocalDateTime.now() )
                                .status( 400 )
                                .method( HttpMethod.resolve(request.getMethod()) )
                                .path( request.getRequestURL().toString() )
                                .build());
    }

    @ExceptionHandler(TuteurNotExistingException.class)
    public ResponseEntity<ErrorDTO> handleException(TuteurNotExistingException ex, HttpServletRequest request){

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(
                        ErrorDTO.builder()
                                .message( ex.getMessage() )
                                .receivedAt( LocalDateTime.now() )
                                .status( 422 )
                                .method( HttpMethod.resolve(request.getMethod()) )
                                .path( request.getRequestURL().toString() )
                                .build()
                );
    }

    @ExceptionHandler(FormValidationException.class)
    public ResponseEntity<ErrorDTO> handleException(FormValidationException ex, HttpServletRequest req){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorDTO.builder()
                                .message(ex.getMessage())
                                .receivedAt( LocalDateTime.now() )
                                .status( 400 )
                                .method( HttpMethod.resolve(req.getMethod()) )
                                .path( req.getRequestURL().toString() )
                                .build()
                                .addInfo("errors", ex.getMessages())
                );
    }

}
