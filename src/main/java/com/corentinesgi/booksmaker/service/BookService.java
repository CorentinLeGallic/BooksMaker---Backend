package com.corentinesgi.booksmaker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.corentinesgi.booksmaker.model.Book;
import com.corentinesgi.booksmaker.repository.BookRepository;

@Service
public class BookService {
  private final BookRepository repo;

  public BookService(BookRepository repo) {
    this.repo = repo;
  }

  public List<Book> findAll() {
    return repo.findAll();
  }

  public Book create(Book book) {
    return repo.save(book);
  }

  public Book update(Long id, Book book) {
    book.setId(id);
    return repo.save(book);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }
}
