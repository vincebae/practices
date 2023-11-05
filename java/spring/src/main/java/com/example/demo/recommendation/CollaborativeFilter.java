package com.example.demo.recommendation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("CF")
@Primary
public class CollaborativeFilter implements Filter {
  public String[] getRecommendations(String movie) {
    return new String[] {"Finding Nemo", "Ice Age", "Toy Story"};
  }
}
