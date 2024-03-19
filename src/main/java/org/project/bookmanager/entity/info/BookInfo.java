package org.project.bookmanager.entity.info;

import org.project.bookmanager.entity.Book;

import java.time.LocalDate;

public class BookInfo {
  private String isbn;
  private long janCode1;
  private long janCode2;
  private String title;
  private long authorId;
  private long publisherId;
  private LocalDate dateOfPublication;

  public BookInfo() {

  }

  public BookInfo(Book book) {
    this(
      book.isbn(),
      book.janCode1(),
      book.janCode2(),
      book.title(),
      book.authorId(),
      book.publisherId(),
      LocalDate.parse(book.dateOfPublication())
    );
  }

  public BookInfo(String isbn, long janCode1, long janCode2, String title, long authorId, long publisherId, LocalDate dateOfPublication) {
    this.isbn = isbn;
    this.janCode1 = janCode1;
    this.janCode2 = janCode2;
    this.title = title;
    this.authorId = authorId;
    this.publisherId = publisherId;
    this.dateOfPublication = dateOfPublication;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public long getJanCode1() {
    return janCode1;
  }

  public void setJanCode1(long janCode1) {
    this.janCode1 = janCode1;
  }

  public long getJanCode2() {
    return janCode2;
  }

  public void setJanCode2(long janCode2) {
    this.janCode2 = janCode2;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(long authorId) {
    this.authorId = authorId;
  }

  public long getPublisherId() {
    return publisherId;
  }

  public void setPublisherId(long publisherId) {
    this.publisherId = publisherId;
  }

  public LocalDate getDateOfPublication() {
    return dateOfPublication;
  }

  public void setDateOfPublication(LocalDate dateOfPublication) {
    this.dateOfPublication = dateOfPublication;
  }
}
