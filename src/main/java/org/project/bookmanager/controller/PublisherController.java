package org.project.bookmanager.controller;

import org.project.bookmanager.entity.Publisher;
import org.project.bookmanager.entity.info.PublisherInfo;
import org.project.bookmanager.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book/publisher")
public class PublisherController {
  private final PublisherService publisherService;

  public PublisherController(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  @GetMapping(value="/list")
  public String publisherListView(Model model) {
    var publishers = this.publisherService.getPublisherList();

    model.addAttribute("publishers", publishers);

    return "book/publisher/list";
  }

  @GetMapping(value="/register")
  public String publisherRegisterView(Model model) {
    var publisherInfo = new PublisherInfo();

    model.addAttribute("publisherInfo", publisherInfo);

    return "book/publisher/register";
  }

  @PostMapping(value="/register", params="register")
  public String publisherRegister(Model model,
                                  @ModelAttribute("publisherInfo") PublisherInfo publisherInfo) {
    var publisher = new Publisher(
      publisherInfo.getPublisherId(),
      publisherInfo.getName()
    );

    this.publisherService.registerPublisher(publisher);

    return "redirect:/book/publisher/list";
  }

  @GetMapping(value="/edit/{publisherId}")
  public String publisherEditView(Model model,
                                  @PathVariable(name = "publisherId") long publisherId) {
    var publisher = this.publisherService.getPublisherById(publisherId);
    var publisherInfo = new PublisherInfo(publisher);

    model.addAttribute("publisherInfo", publisherInfo);

    return "book/publisher/edit";
  }

  @PostMapping(value="/edit", params="edit")
  public String publisherEdit(Model model,
                              @ModelAttribute("publisherInfo") PublisherInfo publisherInfo) {
    var publisher = new Publisher(
      publisherInfo.getPublisherId(),
      publisherInfo.getName()
    );

    this.publisherService.editPublisher(publisher);

    return "redirect:/book/publisher/list";
  }
}
