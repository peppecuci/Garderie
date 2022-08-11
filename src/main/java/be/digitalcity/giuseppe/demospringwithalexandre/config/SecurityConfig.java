package be.digitalcity.giuseppe.demospringwithalexandre.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception{

        http.httpBasic();

//        http.authorizeRequests()
//                .antMatchers(HttpMethod.POST, /* si on ajoute HttpMethod.POST on ne pourra modifier que les requests en POST relatives au controller ENFANT */ "/enfant/**");  // antMatchers permets de choissir les requests authorizées
//

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //emepche de creer des cookies

        http.authorizeRequests()
                .antMatchers("/security/test/all").permitAll()
                .antMatchers("/security/test/nobody").denyAll()
                .antMatchers("/security/test/connected").authenticated()
                .antMatchers("/security/test/not-connected").anonymous()
                .antMatchers("/security/test/role/user").hasRole("USER")
                .antMatchers("/security/test/role/admin").hasRole("ADMIN")
                .antMatchers("/security/test/role/any").hasAnyRole("USER", "ADMIN")
                .antMatchers("/security/test/authority/READ").hasAuthority("ROLE_USER")
                .antMatchers("/security/test/role/any").hasAnyAuthority("ROLE_USER", "WRITE")
                .antMatchers("/fake/request/{id::[0-9]+}/**").denyAll()
                .anyRequest().permitAll();

    return http.build();

    }
}
