package ru.progwards.java2.lessons.examples;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Predicate;

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
  }
}
