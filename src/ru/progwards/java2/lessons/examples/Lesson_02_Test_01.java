package ru.progwards.java2.lessons.examples;

import java.util.ArrayList;
import java.util.List;

public class Lesson_02_Test_01
{

  public static void main(String[] args)
  {
    Lesson_02_Test_01 mane = new Lesson_02_Test_01();
    List<Person> list = new ArrayList<>(List.of(
      new Person("CCC", 2),
      new Person("BBB", 3),
      new Person("AAA", 1)
    ));
    Person p = new Person("m", 0);
    p.sortAndPrint(list);
  }
}

class Person
{
  //  Создайте метод, используя лямбда, с сигнатурой
  void sortAndPrint(List<Person> list)
//  , который вначале сортирует list по возрасту, а затем выводит его на консоль.
  {
    list.sort((x, y) -> Integer.compare(x.age, y.age));
    list.forEach(System.out::println);
  }

  private String name;
  private int age;

  public Person(String name, int age)
  {
    this.name = name;
    this.age = age;
  }

  public String toString()
  {
    return name + " " + age;
  }
}


