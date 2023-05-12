package com.works;

import com.works.props.Product;
import com.works.props.RandomUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Configuration
public class AppBeans {


    @Bean
    public WebClient webClient() {
        return WebClient.create("http://localhost:8090/");
    }

    @Bean
    public CommandLineRunner commandLineRunner( WebClient webClient ) {
        return (args -> {
            Flux<Product[]> productFlux = webClient
            .get()
            .uri("all")
            .accept(MediaType.TEXT_EVENT_STREAM)
            .retrieve()
            .bodyToFlux(Product[].class);

            productFlux.subscribe( items -> {
                for( Product item : items ) {
                    System.out.println( item );
                }
            });
        });
    }

    @Bean
    public CommandLineRunner commandLineRunner_1() {
        WebClient webClient = WebClient.create("https://randomuser.me/");
        return ( args -> {
            webClient
            .get()
            .uri("api/?page=0&results=10&seed=abc")
            .accept(MediaType.TEXT_EVENT_STREAM)
            .retrieve()
            .bodyToFlux(RandomUser.class)
            .subscribe( objRandomUser -> {
                objRandomUser.getResults().forEach( item -> {
                    System.out.println( item.getName().getFirst() );
                } );
            });
        });
    }

}
