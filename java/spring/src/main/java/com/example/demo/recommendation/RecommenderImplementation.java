package com.example.demo.recommendation;


public class RecommenderImplementation implements Recommender {

  private final Filter filter;

  RecommenderImplementation(Filter filter) {
    this.filter = filter;
  }

  public String[] recommendMovies(String movie) {
    String[] results = filter.getRecommendations(movie);
    return results;
  }
}
