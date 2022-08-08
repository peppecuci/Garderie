package be.digitalcity.giuseppe.demospringwithalexandre.controller;

import be.digitalcity.giuseppe.demospringwithalexandre.mapper.EnfantMapper;
import be.digitalcity.giuseppe.demospringwithalexandre.mapper.ReservationMapper;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.EnfantDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.ReservationDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.ReservationForm;
import be.digitalcity.giuseppe.demospringwithalexandre.services.EnfantService;
import be.digitalcity.giuseppe.demospringwithalexandre.services.ReservationService;
import be.digitalcity.giuseppe.demospringwithalexandre.services.TuteurService;
import be.digitalcity.giuseppe.demospringwithalexandre.services.impl.ReservationServiceImpl;
import be.digitalcity.giuseppe.demospringwithalexandre.services.impl.TuteurServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {


    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
//        this.mapper = mapper;
//        this.tuteurService = tuteurService;
    }

    @GetMapping("/{id:[0-9]+}")
    public ReservationDTO getOne(@PathVariable long id){

        return service.getOne(id);

    }

    @GetMapping()
    public List<ReservationDTO> getAll(){

        return service.getAll();

    }

    @PostMapping()
    public ReservationDTO insert(@Valid @RequestBody ReservationForm form){

        return service.create(form);

    }

    @PatchMapping ("/cancel/{id}")
    public ReservationDTO cancelByID(@PathVariable long id){

        return service.cancelById(id);

    }

}
