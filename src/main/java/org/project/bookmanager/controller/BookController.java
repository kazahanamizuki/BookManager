package org.project.bookmanager.controller;

import org.project.bookmanager.entity.Author;
import org.project.bookmanager.entity.Book;
import org.project.bookmanager.entity.info.BookInfo;
import org.project.bookmanager.entity.Publisher;
import org.project.bookmanager.service.AuthorService;
import org.project.bookmanager.service.BookService;
import org.project.bookmanager.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/book")
public class BookController {
  private final BookService bookService;
  private final AuthorService authorService;
  private final PublisherService publisherService;

  public BookController(BookService bookService,
                        AuthorService authorService,
                        PublisherService publisherService) {
    this.bookService = bookService;
    this.authorService = authorService;
    this.publisherService = publisherService;
  }

  @GetMapping(value="/list")
  public String bookListView(Model model) {
    var books = bookService.getBookList();
    var authors = authorService.getAuthorList();
    var authorMap = authors.stream()
      .collect(Collectors.toMap(Author::authorId, Author::name));
    var publishers = publisherService.getPublisherList();
    var publisherMap = publishers.stream()
      .collect(Collectors.toMap(Publisher::publisherId, Publisher::name));

    model.addAttribute("books", books);
    model.addAttribute("authorMap", authorMap);
    model.addAttribute("publisherMap", publisherMap);

    return "book/list";
  }

  @GetMapping(value="/register")
  public String bookRegisterView(Model model) {
    var bookInfo = new BookInfo();
    var authors = authorService.getAuthorList();
    var publishers = publisherService.getPublisherList();

    model.addAttribute("bookInfo", bookInfo);
    model.addAttribute("authors", authors);
    model.addAttribute("publishers", publishers);

    return "book/register";
  }

  @PostMapping(value="/register", params="register")
  public String bookRegister(Model model,
                             @ModelAttribute("bookInfo") BookInfo bookInfo) {
    var book = new Book(
      bookInfo.getIsbn(),
      bookInfo.getJanCode1(),
      bookInfo.getJanCode2(),
      bookInfo.getTitle(),
      bookInfo.getAuthorId(),
      bookInfo.getPublisherId(),
      bookInfo.getDateOfPublication().toString()
    );

    this.bookService.registerBook(book);

    return "redirect:/book/list";
  }

  @GetMapping(value="/edit/{isbn}")
  public String bookEditView(Model model,
                             @PathVariable(name = "isbn") String isbn) {
    var book = this.bookService.getBookByISBN(isbn);
    var bookInfo = new BookInfo(book);
    var authors = authorService.getAuthorList();
    var publishers = publisherService.getPublisherList();

    model.addAttribute("bookInfo", bookInfo);
    model.addAttribute("authors", authors);
    model.addAttribute("publishers", publishers);

    return "book/edit";
  }

  @PostMapping(value="/edit", params="edit")
  public String bookEdit(Model model,
                         @ModelAttribute("bookInfo") BookInfo bookInfo) {
    var book = new Book(
      bookInfo.getIsbn(),
      bookInfo.getJanCode1(),
      bookInfo.getJanCode2(),
      bookInfo.getTitle(),
      bookInfo.getAuthorId(),
      bookInfo.getPublisherId(),
      bookInfo.getDateOfPublication().toString()
    );

    this.bookService.editBook(book);

    return "redirect:/book/list";
  }
}
