package org.project.bookmanager.service;

import org.project.bookmanager.datasource.PublisherDao;
import org.project.bookmanager.entity.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
  private PublisherDao dao;

  public PublisherService(PublisherDao dao) {
    this.dao = dao;
  }

  public List<Publisher> getPublisherList() {
    return this.dao.selectAll();
  }

  public Publisher getPublisherById(long publisherId) {
    return this.dao.selectById(publisherId);
  }

  public void registerPublisher(Publisher publisher) {
    this.dao.insert(publisher);
  }

  public void editPublisher(Publisher publisher) {
    this.dao.update(publisher);
  }
}
