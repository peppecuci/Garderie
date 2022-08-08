package be.digitalcity.giuseppe.demospringwithalexandre.controller;

import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.EnfantInsertForm;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.EnfantUpdateForm;
import be.digitalcity.giuseppe.demospringwithalexandre.mapper.EnfantMapper;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.EnfantDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Tuteur;
import be.digitalcity.giuseppe.demospringwithalexandre.services.EnfantService;
import be.digitalcity.giuseppe.demospringwithalexandre.services.TuteurService;
import be.digitalcity.giuseppe.demospringwithalexandre.services.impl.TuteurServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RequestMapping("/enfant")
@RestController
public class EnfantController {

    private final EnfantService service;
    private final EnfantMapper mapper;
    private final TuteurService tuteurService;




    public EnfantController(EnfantService service, EnfantMapper mapper, TuteurService tuteurService, TuteurServiceImpl tuteurServiceimpl) {
        this.service = service;
        this.mapper = mapper;
        this.tuteurService = tuteurService;
    }

    @GetMapping("/{id:[0-9]+}")
    public EnfantDTO getOne(@PathVariable long id){

        return service.getOne(id);

    }

    //GET ALL
    @GetMapping
    public List<EnfantDTO> getAll(){
        return service.getAll();
    }

    //INSERT
    @PostMapping
    public EnfantDTO insert(@Valid @RequestBody EnfantInsertForm form){

        return service.create(form);

    }


    //DELETE
    @DeleteMapping ("/delete/{id:[0-9]+}")
    public EnfantDTO delete(@PathVariable long id){

        return service.delete(id);

    }


    //UPDATE
    @PutMapping("/{id}")
    public EnfantDTO update(@PathVariable long id, @RequestBody EnfantUpdateForm form){

        return  service.update(id, form);

    }


    //TODO UPDATE BY PATCH (Possibilité de mettre à jour un seul parametre ou plusieurs jusqu'à la totalité des paramètres)
    @PatchMapping("/{id:[0-9]+}")
    public EnfantDTO patchTuteurs(@PathVariable long id, @Valid @RequestBody Collection<Long> tuteurIds){
        return service.patchTuteurs(id, tuteurIds);
    }

    @GetMapping(value = "/allergie")
    public List<EnfantDTO> getAllWithAllergie(@RequestParam String allergie){
        return service.getAllWithAllergie(allergie);
    }

}
