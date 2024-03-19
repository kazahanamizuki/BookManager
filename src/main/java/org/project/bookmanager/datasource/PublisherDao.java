package org.project.bookmanager.datasource;

import org.project.bookmanager.entity.Publisher;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublisherDao {
  private static final String SELECT_ALL = """
    SELECT * FROM `publisher`
    """;

  private static final String SELECT_BY_ID = """
    SELECT * FROM `publisher` WHERE `publisher_id` = :publisher_id
    """;

  private static final String INSERT = """
    INSERT INTO `publisher` (
      `publisher_id`, `name`
    ) VALUES (
      :publisher_id,  :name
    )
    """;

  private static final String UPDATE = """
    UPDATE `publisher` SET
      `name` = :name
      WHERE
      `publisher_id` = :publisher_id
    """;

  private static final RowMapper<Publisher> ROW_MAPPER = (rs, rowNum) ->
    new Publisher(
      rs.getLong("publisher_id"),
      rs.getString("name")
    );

  private final NamedParameterJdbcTemplate template;

  public PublisherDao(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  public List<Publisher> selectAll() {
    return template.query(SELECT_ALL, ROW_MAPPER);
  }

  public Publisher selectById(long publisherId) {
    SqlParameterSource params = new MapSqlParameterSource()
      .addValue("publisher_id", publisherId);

    return template.queryForObject(SELECT_BY_ID, params, ROW_MAPPER);
  }

  public void insert(Publisher publisher) {
    SqlParameterSource params = new MapSqlParameterSource()
      .addValue("publisher_id", publisher.publisherId())
      .addValue("name", publisher.name());

    template.update(INSERT, params);
  }

  public void update(Publisher publisher) {
    SqlParameterSource params = new MapSqlParameterSource()
      .addValue("publisher_id", publisher.publisherId())
      .addValue("name", publisher.name());

    template.update(UPDATE, params);
  }
}
