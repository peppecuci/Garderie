package be.digitalcity.giuseppe.demospringwithalexandre.controller;

import be.digitalcity.giuseppe.demospringwithalexandre.forms.EnfantUpdateForm;
import be.digitalcity.giuseppe.demospringwithalexandre.forms.TuteurForm;
import be.digitalcity.giuseppe.demospringwithalexandre.mapper.EnfantMapper;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.EnfantDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import be.digitalcity.giuseppe.demospringwithalexandre.services.EnfantService;
import be.digitalcity.giuseppe.demospringwithalexandre.services.TuteurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/enfant")
@RestController
public class EnfantController {

    private final EnfantService service;
    private final EnfantMapper mapper;
    private TuteurService tuteurService;
    private TuteurForm tuteurForm;



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
    public EnfantDTO insert(@RequestBody EnfantUpdateForm enfantToInsert){
        return mapper.toDto(service.create(mapper.toEntity(enfantToInsert)));
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
        Set<Tuteur> tuteurs = tuteurService.getAllById(form.getTuteursId());
        entity.setTuteurs(tuteurs);
        return mapper.toDto(service.update(id, entity));

    }



}
