package ru.progwards.java2.lessons.recursion;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GoodsWithLambda
{
  List<Goods> list;

  public static void main(String[] args)
  {
    GoodsWithLambda mane = new GoodsWithLambda();
    List<Goods> temp = List.of(
      new Goods("6666", "abcde", 7, 22, Instant.parse("2020-04-08T00:00:00Z")),
      new Goods("5555", "BBBde", 4, 22, Instant.parse("2020-04-07T00:00:00Z")),
      new Goods("4444", "abhjk", 9, 22, Instant.parse("2020-04-06T00:00:00Z")),
      new Goods("3333", "BBBFG", 3, 22, Instant.parse("2020-04-05T00:00:00Z")),
      new Goods("2222", "zxytv", 1, 22, Instant.parse("2020-04-04T00:00:00Z")),
      new Goods("1111", "abcop", 7, 22, Instant.parse("2020-04-03T00:00:00Z"))
    );

    mane.setGoods(temp);
    mane.list.forEach(System.out::println);
    System.out.println("-------------------\n");

    temp=mane.sortByName();
    temp.forEach(System.out::println);
    System.out.println("-------------------\n");

    temp=mane.sortByNumber();
    temp.forEach(System.out::println);
    System.out.println("-------------------\n");

    temp=mane.sortByPartNumber();
    temp.forEach(System.out::println);
    System.out.println("-------------------\n");

    temp=mane.sortByAvailabilityAndNumber();
    temp.forEach(System.out::println);
    System.out.println("-------------------\n");

    temp=mane.сountLess(7);
    temp.forEach(System.out::println);
    System.out.println("-------------------\n");

    temp=mane.сountBetween(1,7);
    temp.forEach(System.out::println);
    System.out.println("-------------------\n");

    temp=mane.expiredAfter(Instant.parse("2020-04-06T00:00:00Z"));
    temp.forEach(System.out::println);

  }

  void setGoods(List<Goods> list)
  {
    this.list = list;
  }

  List<Goods> sortByName()
//  - вернуть список, отсортированный по наименованию
  {
    return list.stream().sorted(Comparator.comparing(x->x.name)).collect(Collectors.toList());
  }

  List<Goods> sortByNumber()
  //- вернуть список, отсортированный по артикулу, без учета регистра
  {
    return list.stream().
      sorted(Comparator.comparing(x -> x.number.toUpperCase())).
      collect(Collectors.toList());
  }

  List<Goods> sortByPartNumber()
  //- вернуть список, отсортированный по первым 3-м символам артикула, без учета регистра
  {
    return list.stream().
      sorted(Comparator.comparing(x -> x.number.substring(0,3).toUpperCase())).
      collect(Collectors.toList());
  }

  //  - вернуть список, отсортированный по количеству,
//  а для одинакового количества, по артикулу, без учета регистра
  List<Goods> sortByAvailabilityAndNumber()
  {
    return list.stream().
      sorted(Comparator.comparing(x -> x.number.toUpperCase())).
      sorted(Comparator.comparing(x -> x.available)).
      collect(Collectors.toList());
  }

  List<Goods> expiredAfter(Instant date)
//  - вернуть список, с товаром, который будет просрочен после указанной даты,
//  отсортированный по дате годности
  {
    return list.stream().
      sorted(Comparator.comparing(x->x.expired)).
      takeWhile(x->x.expired.isBefore(date)).
      collect(Collectors.toList());
  }

  List<Goods> сountLess(int count)
//  - вернуть список, с товаром, количество на складе которого меньше указанного
  {
    return list.stream().
      sorted(Comparator.comparing(x -> x.available)).
      takeWhile(x->x.available<count).
      collect(Collectors.toList());
  }

  List<Goods> сountBetween(int count1, int count2)
  //- вернуть список, с товаром, количество на складе которого больше count1 и меньше count2
  {
    return list.stream().
      sorted(Comparator.comparing(x -> x.available)).
      takeWhile(x->x.available<count2).
      dropWhile(x->x.available<count1+1).
      collect(Collectors.toList());
  }


}

class Goods
{
  @Override
  public String toString()
  {
    return "Goods{" +
      "name='" + name + '\'' +
      ", number='" + number + '\'' +
      ", available=" + available +
      ", price=" + price +
      ", expired=" + expired +
      '}';
  }

  public Goods(String name, String number, int available, double price, Instant expired)
  {
    this.name = name;
    this.number = number;
    this.available = available;
    this.price = price;
    this.expired = expired;
  }

  String name;// -наименование
  String number;// -артикул
  int available;//  количество на  складе
  double price;// -цена
  Instant expired;//  срок годности
}


