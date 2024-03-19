package org.project.bookmanager.service;

import org.project.bookmanager.datasource.AuthorDao;
import org.project.bookmanager.datasource.BookDao;
import org.project.bookmanager.entity.Author;
import org.project.bookmanager.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
  private AuthorDao dao;

  public AuthorService(AuthorDao dao) {
    this.dao = dao;
  }

  public List<Author> getAuthorList() {
    return this.dao.selectAll();
  }

  public Author getAuthorById(long authorId) {
    return this.dao.selectById(authorId);
  }

  public void registerAuthor(Author author) {
    this.dao.insert(author);
  }

  public void editAuthor(Author author) {
    this.dao.update(author);
  }
}
