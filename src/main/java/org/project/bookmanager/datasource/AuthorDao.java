package org.project.bookmanager.datasource;

import org.project.bookmanager.entity.Author;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorDao {
  private static final String SELECT_ALL = """
    SELECT * FROM `author`
    """;

  private static final String SELECT_BY_ID = """
    SELECT * FROM `author` WHERE `author_id` = :author_id
    """;

  private static final String INSERT = """
    INSERT INTO `author` (
      `author_id`, `name`
    ) VALUES (
      :author_id,  :name
    )
    """;

  private static final String UPDATE = """
    UPDATE `author` SET
      `name` = :name
      WHERE
      `author_id` = :author_id
    """;

  private static final RowMapper<Author> ROW_MAPPER = (rs, rowNum) ->
    new Author(
      rs.getLong("author_id"),
      rs.getString("name")
    );

  private final NamedParameterJdbcTemplate template;

  public AuthorDao(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  public List<Author> selectAll() {
    return template.query(SELECT_ALL, ROW_MAPPER);
  }

  public Author selectById(long authorId) {
    SqlParameterSource params = new MapSqlParameterSource()
      .addValue("author_id", authorId);

    return template.queryForObject(SELECT_BY_ID, params, ROW_MAPPER);
  }

  public void insert(Author author) {
    SqlParameterSource params = new MapSqlParameterSource()
      .addValue("author_id", author.authorId())
      .addValue("name", author.name());

    template.update(INSERT, params);
  }

  public void update(Author author) {
    SqlParameterSource params = new MapSqlParameterSource()
      .addValue("author_id", author.authorId())
      .addValue("name", author.name());

    template.update(UPDATE, params);
  }
}
