package com.corentinesgi.booksmaker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "books")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title", length = 128, nullable = false, unique = false)
  private String title;

  @Column(name = "author", length = 64, nullable = false, unique = false)
  private String author;

  @Column(name = "publish_year", length = 4, nullable = false, unique = false)
  private String publishYear;

  @Column(name = "category", length = 64, nullable = false, unique = false)
  private String category;

  public Book() {}

  public Book(String title, String author, String publishYear, String category) {
    this.title = title;
    this.author = author;
    this.publishYear = publishYear;
    this.category = category; 
  }
}