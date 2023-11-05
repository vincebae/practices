package com.example.demo.recommendation;

import java.util.Optional;
import org.immutables.value.Value;

@Value.Immutable
public interface Movie {

  int id();
  String name();
  Optional<String> genre();
  Optional<String> producer();
  
}
