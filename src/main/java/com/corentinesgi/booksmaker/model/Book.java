package com.corentinesgi.booksmaker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "BOOKS")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "TITLE", length = 128, nullable = false, unique = false)
  private String title;

  @Column(name = "AUTHOR", length = 64, nullable = false, unique = false)
  private String author;

  @Column(name = "PUBLISH_YEAR", length = 4, nullable = false, unique = false)
  private String publishYear;

  @Column(name = "CATEGORY", length = 64, nullable = false, unique = false)
  private String category;

  public Book() {}

  public Book(String title, String author, String publishYear, String category) {
    this.title = title;
    this.author = author;
    this.publishYear = publishYear;
    this.category = category; 
  }
}