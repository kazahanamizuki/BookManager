package com.project.bookmanager;

import org.mockito.Mockito;
import org.project.bookmanager.entity.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

  @Bean
  public Book book() {
    return Mockito.mock(Book.class);
  }
}
