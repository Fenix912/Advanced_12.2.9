package com.example.reactive.controller;

import com.example.reactive.model.Book;
import com.example.reactive.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(value = "/api")
public class RestAdminController {

    private final BookRepository bookRepository;

    public RestAdminController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book")
    public Flux<Book> getAllEntities() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public Mono<Book> getEntityById(@PathVariable Long id) {
        return bookRepository.findById(id);
    }
}
