package ru.progwards.java2.lessons.examples;

import java.util.ArrayList;
import java.util.List;

class Book
{
static List<Book> createList()
{
  List<Book> list = new ArrayList<>(List.of(
    new Book("CCC", "XXX", 2),
    new Book("BBB", "ZZZ", 3),
    new Book("AAA", "YYY", 1)
  ));
return list;
}

  String author;
  String name;
  double price;

  public Book(String author, String name, double price)
  {
    this.author = author;
    this.name = name;
    this.price = price;
  }

  @Override
  public String toString()
  {
    return "Book{" +
      "author='" + author + '\'' +
      ", name='" + name + '\'' +
      ", price=" + price +
      '}';
  }
}
