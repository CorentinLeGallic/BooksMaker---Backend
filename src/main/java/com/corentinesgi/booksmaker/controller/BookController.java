package com.corentinesgi.booksmaker.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
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
  public ResponseEntity<List<Book>> getAll() {
    List<Book> results = service.findAll();
    return ResponseEntity.status(HttpStatus.CREATED).body(results);
  }

  @PostMapping
  public ResponseEntity<Book> create(@RequestBody Book book) {
    Book createdBook = service.create(book);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
  }

  @PutMapping("{id}")
  public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
    Boolean bookExists = service.exists(id);

    if (!bookExists) ResponseEntity.status(HttpStatus.NOT_FOUND);
    if (id != book.getId()) ResponseEntity.status(HttpStatus.BAD_REQUEST);

    Book result = service.update(id, book);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
