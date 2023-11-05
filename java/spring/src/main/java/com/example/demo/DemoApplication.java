package com.example.demo;

import com.example.demo.recommendation.Movie;
import com.example.demo.recommendation.Recommender;
import com.example.demo.recommendation.RecommenderService;
import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext appContext = SpringApplication.run(DemoApplication.class, args);

    RecommenderService recommenderService = appContext.getBean(RecommenderService.class);
    String[] result = recommenderService.recommend();
    System.out.println(Arrays.toString(result));

    Recommender recommender = appContext.getBean(Recommender.class);
    result = recommender.recommendMovies("Finding Dory");
    System.out.println(Arrays.toString(result));

    Movie movie1 = appContext.getBean("movie1", Movie.class);
    Movie movie2 = appContext.getBean("movie2", Movie.class);
    Movie movie3 = appContext.getBean("movie3", Movie.class);

    System.out.println("Movie 1: " + movie1);
    System.out.println("Movie 2: " + movie2);
    System.out.println("Movie 3: " + movie3);

    boolean hasGenre = movie3.genre().isPresent();
    boolean hasProducer = movie3.producer().isPresent();
    System.out.println("hasGenre: " + hasGenre + ", hasProducer: " + hasProducer);
  }
}
