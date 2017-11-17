package com.tca.webapp.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(scanBasePackages = { "com.tca" })
@EnableJpaRepositories(basePackages = "com.tca.dao.access")
@EntityScan(basePackages = "com.tca.dao.model")
public class TcaApplication extends WebMvcConfigurerAdapter {

  public static void main(String[] args) {
    SpringApplication.run(TcaApplication.class, args);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!registry.hasMappingForPattern("/webjars/**")) {
      registry.addResourceHandler("/webjars/**").addResourceLocations(
          "classpath:/META-INF/resources/webjars/");
    }
  }
}
