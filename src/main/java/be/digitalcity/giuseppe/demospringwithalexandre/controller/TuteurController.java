package be.digitalcity.giuseppe.demospringwithalexandre.controller;


import be.digitalcity.giuseppe.demospringwithalexandre.exceptions.TuteurNotExistingException;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.TuteurForm;
import be.digitalcity.giuseppe.demospringwithalexandre.mapper.TuteurMapper;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.TuteurDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import be.digitalcity.giuseppe.demospringwithalexandre.services.TuteurService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @GetMapping("/{id:[0-9]+}")
    public TuteurDTO getOne(@PathVariable long id){

        return service.getOne(id);

    }

    @GetMapping
    public List<TuteurDTO> getAll(){
        return service.getAll();
    }

    @PostMapping
    public TuteurDTO insert(@Valid @RequestBody TuteurForm form){
        return service.create(form);
    }

    @DeleteMapping("/delete/{id:[0-9]+}")
    public TuteurDTO delete(@PathVariable long id){
        return service.delete(id);
    }



    @PutMapping("/{id}")
    public TuteurDTO update(@PathVariable long id, @RequestBody (required = false) TuteurForm form){

            return service.update(id, form);

    }

    @GetMapping(params = "ville")
    public List<TuteurDTO> getAllFromVille(@RequestParam String ville){
        return service.getAllFromVilleWithChild(ville);
    }



}
