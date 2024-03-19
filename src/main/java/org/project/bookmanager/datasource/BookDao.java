package org.project.bookmanager.datasource;

import org.project.bookmanager.entity.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDao {
  private static final String SELECT_ALL = """
    SELECT * FROM `book`
    """;

  private static final String SELECT_BY_ISBN = """
    SELECT * FROM `book` WHERE `isbn` = :isbn
    """;

  private static final String INSERT = """
    INSERT INTO `book` (
      `isbn`, `jan_code_1`, `jan_code_2`, `author_id`, `publisher_id`, `title`, `date_of_publication`
    ) VALUES (
      :isbn,  :jan_code_1,  :jan_code_2,  :author_id,  :publisher_id,  :title,  :date_of_publication
    )
    """;

  private static final String UPDATE = """
    UPDATE `book` SET
      `jan_code_1` = :jan_code_1,
      `jan_code_2` = :jan_code_2,
      `author_id` = :author_id,
      `publisher_id` = :publisher_id,
      `title` = :title,
      `date_of_publication` = :date_of_publication
      WHERE
      `isbn` = :isbn
    """;

  private static final RowMapper<Book> ROW_MAPPER = (rs, rowNum) ->
    new Book(
      rs.getString("isbn"),
      rs.getLong("jan_code_1"),
      rs.getLong("jan_code_2"),
      rs.getString("title"),
      rs.getLong("author_id"),
      rs.getLong("publisher_id"),
      rs.getString("date_of_publication")
    );

  private final NamedParameterJdbcTemplate template;

  public BookDao(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  public List<Book> selectAll() {
    return template.query(SELECT_ALL, ROW_MAPPER);
  }

  public Book selectByISBN(String isbn) {
    SqlParameterSource params = new MapSqlParameterSource()
      .addValue("isbn", isbn);

    return template.queryForObject(SELECT_BY_ISBN, params, ROW_MAPPER);
  }

  public void insert(Book book) {
    SqlParameterSource params = new MapSqlParameterSource()
      .addValue("isbn", book.isbn())
      .addValue("jan_code_1", book.janCode1())
      .addValue("jan_code_2", book.janCode2())
      .addValue("title", book.title())
      .addValue("author_id", book.authorId())
      .addValue("publisher_id", book.publisherId())
      .addValue("date_of_publication", book.dateOfPublication());

    template.update(INSERT, params);
  }

  public void update(Book book) {
    SqlParameterSource params = new MapSqlParameterSource()
      .addValue("isbn", book.isbn())
      .addValue("jan_code_1", book.janCode1())
      .addValue("jan_code_2", book.janCode2())
      .addValue("title", book.title())
      .addValue("author_id", book.authorId())
      .addValue("publisher_id", book.publisherId())
      .addValue("date_of_publication", book.dateOfPublication());

    template.update(UPDATE, params);
  }
}
