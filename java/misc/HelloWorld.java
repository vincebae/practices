package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class HelloWorld {

  public static void main(String[] args) {
    System.out.println("Hello World!");

    ArrayList<String> array = new ArrayList<>();
    array.add("a");
    array.add("b");
    array.add("c");
    array.add("d");
    array.add("e");
    array.stream().forEach(System.out::println);

    int[] intArray = IntStream.range(0, 10).toArray();
    Arrays.stream(intArray).map(i -> i * 2).map(i -> i - 3).boxed().forEach(System.out::println);

    for (int k = 0; k < intArray.length; k++) {
      
    }

  }

}
