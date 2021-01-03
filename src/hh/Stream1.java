package hh;

import java.util.stream.IntStream;

public class Stream1 {
   public static void main(String[] args) {
      System.out.println(IntStream.iterate(1, i -> i + 1)
              .parallel()
              .limit(10)
              .reduce(0, (x, y) -> x - y));
      System.out.println(IntStream.iterate(1, i -> i + 1)
              .limit(10)
              .reduce(0, (x, y) -> x - y));
      //#############
      System.out.println(IntStream.iterate(1, i -> i + 1)
              .parallel()
              .limit(10)
              .reduce(0, (x, y) -> x + y));
      System.out.println(IntStream.iterate(1, i -> i + 1)
              .limit(10)
              .reduce(0, (x, y) -> x + y));
   }
}
