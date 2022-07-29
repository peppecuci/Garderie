package be.digitalcity.giuseppe.demospringwithalexandre.controller;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Personne;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestController
@RequestMapping("/demo")
public class DemoController {

    //Pour GET une personne
    //GET - http://localhost:8080/personne ce-ci est l'URL qu'on doit rentrer pour avoir access à cette methode
    @GetMapping(path="/personne")
    @ResponseBody
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public Personne getPersonne(){
        //return new Personne("alex", "kim");
        return new Enfant();
    }

    //Pour recuperer une personne en consolle
    @PostMapping(path="/personne")
    public void writePersone(@RequestBody Personne personne, @RequestParam("repetition") int iter){

        for (int i = 0; i < iter ; i++) {
            System.out.println(personne);
        }


    }

    //Pour recuperer les parametres d'une requete en consolle
    @GetMapping(path="/params")
    public void writeParams(@RequestParam Map<String, Object> params){

        for(String key : params.keySet()) {
            System.out.println(key + " - " + params.get(key));
        }

    }

    //Pour recuperer un header en consolle
    //GET - http://localhost:8080/headers
    @GetMapping(path="/headers")
    public void writeArtificialHeader(@RequestHeader String artificial){

        System.out.println(artificial);

    }

    //Pour recuperer tous les headers
    @GetMapping(path="/headers/all")
    public void writeAllHeaders(@RequestHeader HttpHeaders headers){

        for(String key : headers.keySet()){
            System.out.println(key + " - " + headers.get(key));
        }

    }

    //Pour recuperer un id en consolle
    //http://localhots:8080/demo/{id:[0-8]{0,3}} pour trouver un id constituit de maximim 3 chifre et chaque chifre doit etre entre entre 0 et 8
    //http://localhots:8080/demo/{id:[0-8]+} pour trouver un id constituit de chifre entre 0 et 8. Si on ne met pas un reange comme en haut alors l'id est d'un seul chifre
    //GET - http://localhots:8080/demo/{variable}
    @GetMapping(path="/demo/{id}")
    public void demo(@PathVariable int id){

        System.out.println("L'id selectioné est: " + id);

    }

    //Pour recuperer toute la requete (adresse de reference) et toute la reponse (adresse de referance) en consolle
    @GetMapping(path="/request")
    public void recupRequest(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request);
        System.out.println(response);
    }

    @GetMapping(path="/personne/new")
    public ResponseEntity<Personne> getPersonne(@RequestParam("lastname") String lastName, @RequestParam("firstname") String fistName){
        Personne body = new Enfant();

        HttpHeaders headers = new HttpHeaders();
        headers.add("artificial", "ma valeur");

        HttpStatus status = HttpStatus.OK;

        //return new ResponseEntity<>(body, headers, status); MEME CHOSE QU'EN BAS
        return ResponseEntity.status(status)
                .header("arbitrary", "ma valeur", "2e valeur")
                .headers(headers)
                .body(body);

    }
}
