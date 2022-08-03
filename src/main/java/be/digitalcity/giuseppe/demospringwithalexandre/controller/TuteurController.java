package be.digitalcity.giuseppe.demospringwithalexandre.controller;


import be.digitalcity.giuseppe.demospringwithalexandre.ElementNotFoundException;
import be.digitalcity.giuseppe.demospringwithalexandre.forms.TuteurForm;
import be.digitalcity.giuseppe.demospringwithalexandre.mapper.TuteurMapper;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.ErrorDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.TuteurDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import be.digitalcity.giuseppe.demospringwithalexandre.services.TuteurService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/tuteur")
@RestController
public class TuteurController {

    public TuteurService service;

    public TuteurMapper mapper;

    public TuteurController(TuteurService service, TuteurMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    //CHECKED
    @GetMapping("/{id:[0-9]+}")
    public TuteurDTO getOne(@PathVariable long id){

        return mapper.toDto(service.getOne(id));

    }


    @PostMapping
    public TuteurDTO insert(@RequestBody TuteurForm form){
        return mapper.toDto(service.create(mapper.toEntity(form)));
    }


    @GetMapping
    public List<TuteurDTO> getAll(){
        return service.getAll().stream().map(mapper::toDto)
                .toList();
    }


    @DeleteMapping("/delete/{id:[0-9]+}")
    public TuteurDTO delete(@PathVariable long id){
        return mapper.toDto(service.delete(id));
    }



    @PutMapping("/{id}")
    public TuteurDTO update(@PathVariable long id, @RequestBody TuteurForm form){

        Tuteur entity = mapper.toEntity(form);
        entity.setId(id);
        return mapper.toDto(service.update(id, entity));

    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<?> handleException(ElementNotFoundException ex, HttpServletRequest req){


        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorDTO.builder()
                                .message( ex.getMessage() )
                                .receivedAt( LocalDateTime.now() )
                                .status( HttpStatus.NOT_FOUND )
                                .method( HttpMethod.resolve(req.getMethod()) )
                                .path( req.getRequestURL().toString() )
                                .build());


    }


}
