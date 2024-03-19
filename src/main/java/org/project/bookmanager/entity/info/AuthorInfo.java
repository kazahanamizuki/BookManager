package org.project.bookmanager.entity.info;

import org.project.bookmanager.entity.Author;

public class AuthorInfo {
  private long authorId;
  private String name;

  public AuthorInfo() {}

  public AuthorInfo(Author author) {
    this(
      author.authorId(),
      author.name()
    );
  }

  public AuthorInfo(long authorId, String name) {
    this.authorId = authorId;
    this.name = name;
  }

  public long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(long authorId) {
    this.authorId = authorId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
