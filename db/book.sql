USE books_db;

CREATE TABLE IF NOT EXISTS book (
  `isbn` VARCHAR(32) NOT NULL,
  `jan_code_1` bigint NOT NULL,
  `jan_code_2` bigint NOT NULL,
  `title` VARCHAR(1024) NOT NULL,
  `author_id` bigint NOT NULL,
  `publisher_id` bigint NOT NULL,
  `date_of_publication` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`isbn`)
) engine = InnoDB
  DEFAULT charset = `utf8mb4`
  COLLATE = `utf8mb4_bin`;

CREATE TABLE IF NOT EXISTS author (
  `author_id` bigint NOT NULL,
  `name` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`author_id`)
) engine = InnoDB
  DEFAULT charset = `utf8mb4`
  COLLATE = `utf8mb4_bin`;

CREATE TABLE IF NOT EXISTS publisher (
  `publisher_id` bigint NOT NULL,
  `name` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`publisher_id`)
) engine = InnoDB
  DEFAULT charset = `utf8mb4`
  COLLATE = `utf8mb4_bin`;