package org.project.bookmanager.entity;

public record Book(
  String isbn,
  long janCode1,
  long janCode2,
  String title,
  long authorId,
  long publisherId,
  String dateOfPublication
) {

  public long price() {
    return (janCode2 / 10) % 100000;
  }
}
