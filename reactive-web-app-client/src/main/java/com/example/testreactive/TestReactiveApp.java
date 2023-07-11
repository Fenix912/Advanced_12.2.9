package com.example.testreactive;

import com.example.testreactive.model.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
public class TestReactiveApp {
    public static void main(String[] args) {
        SpringApplication.run(TestReactiveApp.class, args);
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner commandLineRunner(WebClient webClient) {
        return args -> {
            // Получение всех сущностей
            webClient.get()
                    .uri("http://localhost:8080/api/book")
                    .retrieve()
                    .bodyToFlux(Book.class)
                    .doOnNext(System.out::println)
                    .last()
                    .flatMap(book -> {
                        // Получение сущности по id
                        String id = String.valueOf(book.getId());
                        return webClient.get()
                                .uri("http://localhost:8080/api/book/{id}", id)
                                .retrieve()
                                .bodyToMono(Book.class);
                    })
                    .subscribe(System.out::println);
        };
    }
}