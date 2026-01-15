package com.corentinesgi.booksmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corentinesgi.booksmaker.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}