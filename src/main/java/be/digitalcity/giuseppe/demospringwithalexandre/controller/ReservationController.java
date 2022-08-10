package be.digitalcity.giuseppe.demospringwithalexandre.controller;

import be.digitalcity.giuseppe.demospringwithalexandre.mapper.EnfantMapper;
import be.digitalcity.giuseppe.demospringwithalexandre.mapper.ReservationMapper;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.EnfantDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.ReservationDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Reservation;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.ReservationForm;
import be.digitalcity.giuseppe.demospringwithalexandre.services.EnfantService;
import be.digitalcity.giuseppe.demospringwithalexandre.services.ReservationService;
import be.digitalcity.giuseppe.demospringwithalexandre.services.TuteurService;
import be.digitalcity.giuseppe.demospringwithalexandre.services.impl.ReservationServiceImpl;
import be.digitalcity.giuseppe.demospringwithalexandre.services.impl.TuteurServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
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

    @PatchMapping("/cancel/{id}")
    public ReservationDTO cancelByID(@PathVariable long id){

        return service.cancelById(id);

    }

    @GetMapping("/child_by_reservation_date")
    public List<EnfantDTO> getAllEnfantByDate(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate checkInHour){

        return service.getAllEnfantByDate(checkInHour.atStartOfDay());

    }

    @GetMapping("/all_future_booking")
    public List<ReservationDTO> getAllFutureReservation(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate checkInHour, Long id){

        return service.getAllByCheckInHourAfter(checkInHour.atTime(23,59), id);

    }

    @GetMapping("/remaining_of_the_month")
    public List<ReservationDTO> getAllRemainingReservationOfTheCurrentMonth(){

        return service.getAllRemainingReservationOfTheCurrentMonth(LocalDate.now().getMonth());

    }

}
