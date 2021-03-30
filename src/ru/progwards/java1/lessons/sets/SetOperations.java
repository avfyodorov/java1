package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Set;

//Реализовать класс SetOperations, операции над множествами целых чисел.
//
//        1.1 Метод public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2)
//        - объединение множеств
//
//        1.2 Метод public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2)
//        - пересечение множеств
//
//        1.3 Метод public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2)
//        - разница множеств
//
//        1.4 Метод public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2)
//        - симметрическая разница
//
//        Кто подзабыл что значит каждая операция, можно глянуть тут
//        https://studopedia.ru/14_138615_operatsii-nad-mnozhestvami-i-ih-svoystva.html или OK - google
public class SetOperations
{
  public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2)
//        - объединение множеств
  {
    Set<Integer> res = new HashSet<>(set1);
    res.addAll(set2);
    return res;
  }

  public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2)
//        - пересечение множеств
  {
    Set<Integer> res = new HashSet<>(set1);
    res.retainAll(set2);
    return res;
  }

  public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2)
//        - разница множеств
  {
    Set<Integer> res = new HashSet<>(set1);
    res.removeAll(set2);
    return res;
  }

  public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2)
//        - симметрическая разница
  {
    Set<Integer> res = difference(set1, set2);
    res.addAll(difference(set2, set1));
    return res;
  }

  public static void main(String[] args)
  {
    Set<Integer> set1 = Set.of(1, 3, 5, 7, 9);
    Set<Integer> set2 = Set.of(1, 4, 5, 8, 13);
    System.out.println(set1);
    System.out.println(set2);
    System.out.println("union - " + union(set1, set2));
    System.out.println("intersection - " + intersection(set1, set2));
    System.out.println("difference - " + difference(set1, set2));
    System.out.println("sym diff - " + symDifference(set1, set2));


  }
}
