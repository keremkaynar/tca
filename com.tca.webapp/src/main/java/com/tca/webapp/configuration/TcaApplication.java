package com.tca.webapp.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.tca" })
@EnableJpaRepositories(basePackages = "com.tca.dao.access")
@EntityScan(basePackages = "com.tca.dao.model")
public class TcaApplication {

  public static void main(String[] args) {
    SpringApplication.run(TcaApplication.class, args);
  }
}
