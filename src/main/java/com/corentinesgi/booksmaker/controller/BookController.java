package com.corentinesgi.booksmaker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corentinesgi.booksmaker.model.Book;
import com.corentinesgi.booksmaker.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
  private final BookService service;

  public BookController(BookService service) {
    this.service = service;
  }

  @GetMapping
  public List<Book> getAll() {
    return service.findAll();
  }

  @PostMapping
  public ResponseEntity<Book> create(@RequestBody Book book) {
    Book createdBook = service.create(book);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
  }

  @PutMapping("{id}")
  public Book update(@PathVariable Long id, @RequestBody Book book) {
    return service.update(id, book);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
