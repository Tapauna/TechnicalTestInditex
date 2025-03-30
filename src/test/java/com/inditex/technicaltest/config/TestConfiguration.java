package com.inditex.technicaltest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TestConfiguration {

  @Bean
  public TestRestTemplate restTemplate() {
    RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
        .rootUri("http://localhost:8080");

    return new TestRestTemplate(restTemplateBuilder);
  }
}
