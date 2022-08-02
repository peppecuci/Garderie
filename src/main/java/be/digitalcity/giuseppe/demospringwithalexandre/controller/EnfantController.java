package be.digitalcity.giuseppe.demospringwithalexandre.controller;

import be.digitalcity.giuseppe.demospringwithalexandre.forms.EnfantUpdateForm;
import be.digitalcity.giuseppe.demospringwithalexandre.mapper.EnfantMapper;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.EnfantDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.services.EnfantService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.util.List;
import java.util.Set;

@RequestMapping("/enfant")
@RestController
public class EnfantController {

    private EnfantService service;
    private EnfantMapper mapper;



    public EnfantController(EnfantService service, EnfantMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id:[0-9]+}")
    public EnfantDTO getOne(@PathVariable long id){

        return mapper.toDto(service.getOne(id));

    }

    //INSERT
    @PostMapping
    public EnfantDTO insert(@RequestBody Enfant enfantToInsert){
        return mapper.toDto(service.create(enfantToInsert));
    }

    //GET ALL
    @GetMapping
    public List<EnfantDTO> getAll(){
        return service.getAll().stream().map(mapper::toDto)
                .toList();
    }

    //DELETE
    @DeleteMapping ("/delete/{id:[0-9]+}")
    public Enfant delete(@PathVariable long id){
        return service.delete(id);
    }


    //UPDATE
    @PutMapping("/{id}")
    public EnfantDTO update(@PathVariable long id, @RequestBody EnfantUpdateForm form){

        Enfant entity = mapper.toEntity(form);
        entity.setId(id);
        return mapper.toDto(service.update(id, entity));

    }



}
