package be.digitalcity.giuseppe.demospringwithalexandre.controller;

import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.LoginForm;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.UtilisateurCreateForm;
import be.digitalcity.giuseppe.demospringwithalexandre.services.impl.CustomUserDetailsServiceImpl;
import be.digitalcity.giuseppe.demospringwithalexandre.utils.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UtilisateurController {

    private final CustomUserDetailsServiceImpl service; //INJECTION DIRECTEMENT PAR LE SERVICEIMPL MAIS FAUDRAI INJECTER PAR LE SERVICE
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;

    public UtilisateurController(CustomUserDetailsServiceImpl service, AuthenticationManager authManager, JwtProvider jwtProvider) {
        this.service = service;
        this.authManager = authManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public void createUser(@Valid @RequestBody UtilisateurCreateForm form){

        service.create(form);

    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginForm form){

        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));
        return jwtProvider.createToken(auth);

    }
    

}
