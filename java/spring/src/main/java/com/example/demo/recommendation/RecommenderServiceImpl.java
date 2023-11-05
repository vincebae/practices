package com.example.demo.recommendation;

public class RecommenderServiceImpl implements RecommenderService {

  private final Recommender recommender;

  public RecommenderServiceImpl(Recommender recommender) {
    this.recommender = recommender;
  }

  public String[] recommend() {
    return recommender.recommendMovies("");
  }
}
