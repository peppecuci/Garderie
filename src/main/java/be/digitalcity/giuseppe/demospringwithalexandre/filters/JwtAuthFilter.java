package be.digitalcity.giuseppe.demospringwithalexandre.filters;

import be.digitalcity.giuseppe.demospringwithalexandre.utils.JwtProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    public JwtAuthFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Sortir le token de la requete (header Authorization)
        String token = jwtProvider.extractToken(request); // attention peut etre null
        // Verifierla validité du token (algo, secret, claims particuliers)
        if( token != null && jwtProvider.validate(token) ) {
            // Créer l'Authentification
            Authentication auth = jwtProvider.generateAuth(token);
            // mettre l'Authentication dans le context de securité
            SecurityContextHolder.getContext().setAuthentication( auth );

        }

        filterChain.doFilter(request, response);
    }
}
