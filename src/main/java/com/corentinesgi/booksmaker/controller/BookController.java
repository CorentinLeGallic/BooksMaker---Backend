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

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/books")
public class BookController {
  private final BookService service;

  public BookController(BookService service) {
    this.service = service;
  }

  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Livres récupérés avec succès",
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class)))
    ),
  })
  @GetMapping
  public ResponseEntity<List<Book>> getAll() {
    List<Book> results = service.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(results);
  }

  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "201",
      description = "Livre créé avec succès",
      content = @Content(schema = @Schema(implementation = Book.class))
    ),
  })
  @PostMapping
  public ResponseEntity<Book> create(@RequestBody Book book) {
    Book createdBook = service.create(book);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
  }

  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Livre mis-à-jour avec succès",
      content = @Content(schema = @Schema(implementation = Book.class))
    ),
    @ApiResponse(
      responseCode = "404",
      description = "Livre non-trouvé",
      content = @Content(schema = @Schema(hidden = true))
    ),
    @ApiResponse(
      responseCode = "400",
      description = "L'identifiant passé en paramètre et dans l'object Book ne correspondent pas",
      content = @Content(schema = @Schema(hidden = true))
    )
  })
  @PutMapping("{id}")
  public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
    Boolean bookExists = service.exists(id);

    if (!bookExists) return ResponseEntity.notFound().build();
    if (id != book.getId()) return ResponseEntity.badRequest().build();

    Book result = service.update(id, book);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "204",
      description = "Livre supprimé avec succès",
      content = @Content(schema = @Schema(hidden = true))
    ),
    @ApiResponse(
      responseCode = "404",
      description = "Livre non-trouvé",
      content = @Content(schema = @Schema(hidden = true))
    ),
  })
  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!service.exists(id)) return ResponseEntity.notFound().build();
    
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
