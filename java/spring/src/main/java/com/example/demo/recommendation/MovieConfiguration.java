package com.example.demo.recommendation;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieConfiguration {

  @Bean("movie1")
  public Movie movie1() {
    return ImmutableMovie.builder()
        .id(1)
        .name("name")
        .genre(Optional.of("genre"))
        .producer(Optional.of("producer"))
        .build();
  }

  @Bean("movie2")
  public Movie movie2() {
    return ImmutableMovie.builder()
        .id(2)
        .name("name 2")
        .genre("genre 2")
        .producer("producer 2")
        .build();
  }

  @Bean("movie3")
  public Movie movie3() {
    return ImmutableMovie.builder().id(3).name("name 3").genre("genre 3").build();
  }

  @Bean
  public Recommender recommender(@Qualifier("CBF") Filter filter) {
    return new RecommenderImplementation(filter);
  }

  @Bean
  public RecommenderService recommenderService(Recommender recommender) {
    return new RecommenderServiceImpl(recommender);
  }
}
