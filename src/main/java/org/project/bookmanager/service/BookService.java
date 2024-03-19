package org.project.bookmanager.service;

import org.project.bookmanager.datasource.BookDao;
import org.project.bookmanager.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
  private BookDao dao;

  public BookService(BookDao dao) {
    this.dao = dao;
  }

  public List<Book> getBookList() {
    return this.dao.selectAll();
  }

  public Book getBookByISBN(String isbn) {
    return this.dao.selectByISBN(isbn);
  }

  public void registerBook(Book book) {
    this.dao.insert(book);
  }

  public void editBook(Book book) {
    this.dao.update(book);
  }
}
