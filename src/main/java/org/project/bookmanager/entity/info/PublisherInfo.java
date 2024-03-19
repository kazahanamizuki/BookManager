package org.project.bookmanager.entity.info;

import org.project.bookmanager.entity.Publisher;

public class PublisherInfo {
  private long publisherId;
  private String name;

  public PublisherInfo() {}

  public PublisherInfo(Publisher publisher) {
    this(
      publisher.publisherId(),
      publisher.name()
    );
  }

  public PublisherInfo(long publisherId, String name) {
    this.publisherId = publisherId;
    this.name = name;
  }

  public long getPublisherId() {
    return publisherId;
  }

  public void setPublisherId(long publisherId) {
    this.publisherId = publisherId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
