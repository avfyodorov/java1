package ru.progwards.java2.lessons.examples;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.*;

public class Lesson_02_01
{
  public static void main(String[] args)
  {
//
    List<Integer> list = List.of(1, 2, 3, 4, 5);
//    list.forEach(elem -> System.out.println("npp- " + elem));
    list.forEach(elem -> System.out.println("npp- " + elem));

//сравнение  для сортировки вместо анонимного класса
    TreeSet<Integer> treeSet = new TreeSet<>((o1, o2) -> Integer.compare(o1, o2));
    treeSet.addAll(List.of(-3, 2, 2, 1));
    System.out.println(treeSet);

//
    List<Book> list1 = Book.createList();
    list1.sort((x, y) -> Double.compare(x.price, y.price));
    System.out.println(list1);

    list1.sort((x, y) -> x.author.compareTo(y.author));
    list1.forEach(el -> System.out.println(el));

    list1.sort(Comparator.comparing(x -> x.name));
    System.out.println(list1);

//
    Predicate<Integer> isEven = x -> x % 2 == 0;
    System.out.println(isEven.test(8));

    Function<Double, Double> square = x -> x * x;
    Function<Double, Double> root = x -> Math.sqrt(x);
    Function<Double, Double> mod = x -> root.apply(square.apply(x));

    System.out.println(mod.apply(-5.0));
    //
    Supplier<Integer> rand = () -> (int) (Math.random() * 10 + 1);

    for (int i = 0; i < 10; i++)
    {
      System.out.println(rand.get());
    }

    //
    UnaryOperator<Double> sq = x -> x * x;
    System.out.println(sq.apply(-7.0));

    Predicate<Integer> predicate = Lesson_02_01::isEvan;
    System.out.println(predicate.test(6));

    Consumer<String> consumer=System.out::println;
    consumer.accept("qyqy");

  }

  static boolean isEvan(Integer i)
  {
    return i % 2 == 0;
  }
}
