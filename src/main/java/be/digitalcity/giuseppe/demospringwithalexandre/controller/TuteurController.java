package be.digitalcity.giuseppe.demospringwithalexandre.controller;


import be.digitalcity.giuseppe.demospringwithalexandre.forms.TuteurUpdateForm;
import be.digitalcity.giuseppe.demospringwithalexandre.mapper.TuteurMapper;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.TuteurDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import be.digitalcity.giuseppe.demospringwithalexandre.services.TuteurService;
import org.springframework.web.bind.annotation.*;

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
    public TuteurDTO getOne(long id){

        return mapper.toDto(service.getOne(id));

    }

    @PostMapping
    public TuteurDTO insert(@RequestBody Tuteur tuteurToInsert){
        return mapper.toDto(service.create(tuteurToInsert));
    }

    @GetMapping
    public List<TuteurDTO> getAll(){
        return service.getAll().stream().map(mapper::toDto)
                .toList();
    }

    @DeleteMapping("/delete/{id:[0-9]]+}")
    public Tuteur delete(@PathVariable long id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public TuteurDTO update(@PathVariable long id, TuteurUpdateForm form){

        Tuteur entity = mapper.toEntity(form);
        entity.setId(id);
        return mapper.toDto(service.update(id, entity));

    }


}
