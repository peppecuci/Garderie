package be.digitalcity.giuseppe.demospringwithalexandre.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class BeanConfig {

    //INSTANTIATION D'UNE CLASSE QUE NOUS CONTROLLONS PAS AVEC L'ANNOTATION @BEAN.
    //  Nous pouvons faire ca que dans una classe annoté @Configuration ou dans l'Application meme (@SprignBootApplication)
    @Bean
    public Scanner scanner(){

        return new Scanner(System.in);

    }

}
