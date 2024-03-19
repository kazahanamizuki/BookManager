package org.project.bookmanager.controller;

import org.project.bookmanager.entity.Author;
import org.project.bookmanager.entity.info.AuthorInfo;
import org.project.bookmanager.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book/author")
public class AuthorController {
  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping(value="/list")
  public String authorListView(Model model) {
    var authors = this.authorService.getAuthorList();

    model.addAttribute("authors", authors);

    return "book/author/list";
  }

  @GetMapping(value="/register")
  public String authorRegisterView(Model model) {
    var authorInfo = new AuthorInfo();

    model.addAttribute("authorInfo", authorInfo);

    return "book/author/register";
  }

  @PostMapping(value="/register", params="register")
  public String authorRegister(Model model,
                               @ModelAttribute("authorInfo") AuthorInfo authorInfo) {
    var author = new Author(
      authorInfo.getAuthorId(),
      authorInfo.getName()
    );

    this.authorService.registerAuthor(author);

    return "redirect:/book/author/list";
  }

  @GetMapping(value="/edit/{authorId}")
  public String authorEditView(Model model,
                               @PathVariable(name = "authorId") long authorId) {
    var author = this.authorService.getAuthorById(authorId);
    var authorInfo = new AuthorInfo(author);

    model.addAttribute("authorInfo", authorInfo);

    return "book/author/edit";
  }

  @PostMapping(value="/edit", params="edit")
  public String authorEdit(Model model,
                           @ModelAttribute("authorInfo") AuthorInfo authorInfo) {
    var author = new Author(
      authorInfo.getAuthorId(),
      authorInfo.getName()
    );

    this.authorService.editAuthor(author);

    return "redirect:/book/author/list";
  }
}
