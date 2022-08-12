package be.digitalcity.giuseppe.demospringwithalexandre.services.impl;

import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Utilisateur;
import be.digitalcity.giuseppe.demospringwithalexandre.model.forms.UtilisateurCreateForm;
import be.digitalcity.giuseppe.demospringwithalexandre.repositories.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UtilisateurRepository repository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsServiceImpl(UtilisateurRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("connexion impossible"));
    }

    public void create(UtilisateurCreateForm toCreate){

        Utilisateur utilisateur = toCreate.toEntity();
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        repository.save( utilisateur);


    }

}
