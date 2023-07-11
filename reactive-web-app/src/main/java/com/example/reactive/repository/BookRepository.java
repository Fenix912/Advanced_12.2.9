package com.example.reactive.repository;

import com.example.reactive.model.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
}