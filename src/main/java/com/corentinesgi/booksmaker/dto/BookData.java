package com.corentinesgi.booksmaker.dto;

public class BookData {
  private String title;
  private String author;
  private String publishYear;
  private String category;

  public BookData() {}

  public BookData(String title, String author, String publishYear, String category) {
    this.title = title;
    this.author = author;
    this.publishYear = publishYear;
    this.category = category;
  }

  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }

  public String getAuthor() { return author; }
  public void setAuthor(String author) { this.author = author; }

  public String getPublishYear() { return publishYear; }
  public void setPublishYear(String publishYear) { this.publishYear = publishYear; }

  public String getCategory() { return category; }
  public void setCategory(String category) { this.category = category; }
}
