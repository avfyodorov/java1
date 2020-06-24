package ru.progwards.java2.lessons.examples;

import java.util.List;
import java.util.stream.Collectors;

public class Lesson_02_02
{
  public static void main(String[] args)
  {
    //
    List<Book> list = Book.createList();
    List<Book> filtered = list.stream().filter(x -> x.name.contains("X")).collect(Collectors.toList());
    filtered.forEach(System.out::println);

//
    List<Integer> integerList = List.of(5, 8, 4, 9, 23, 1, 6, 10);
    int max = integerList.stream().reduce(0, (x, y) -> x > y ? x : y);
    System.out.println("max=" + max);
  }
}
