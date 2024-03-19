package org.project.bookmanager;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = "org.project.bookmanager")
public class Application {
  public static void main(String[] args) {
    SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
    builder
      .bannerMode(Banner.Mode.CONSOLE)
      .web(WebApplicationType.SERVLET)
      .run(args);
  }
}
