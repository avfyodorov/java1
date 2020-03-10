package ru.progwards.java1.lessons.queues;

//Создать класс - очередь, на обслуживание заявок клиентов в зависимости от величины суммы заказа.
//
//        2.1 Создать отдельный класс Order
//
//        2.2 Создать приватное свойство double sum  - сумма заказа
//
//        2.3 Создать приватное свойство int num  - номер заказа
//        2.4 Создать конструктор public Order(double sum) - для номера заказа создать
//        систему автонумерации, начиная с 1
//
//        2.5 Создать геттер public double getSum()
//        2.6 Создать геттер public int getNum()
//
//
//        Создать класс OrderQueue
//
//        2.7 Создать метод, public void add(Order order), размещающий заказы в очередь с приоритетом,
//        разбивая их по 3-м классам
//
//        3 - заказы до 10000 руб включительно
//        2 - заказы от 10000 до 20000 руб включительно
//        1 - заказы от 20000 руб
//
//        2.8 Создать метод, public Order get(), возвращающий первый заказ в очереди для обслуживания.
//        Вначале обслуживаются заказы класса 1, потом 2, потом 3.
//        Внутри каждого класса заказы должны обслуживаться в порядке поступления (по номеру заказа).
//        Метод не выбрасывает исключения, возвращает null в случае пустой очереди.
//
//        Продумать, и, при необходимости, добавить в классы нужные методы и свойства, для того,
//        чтобы реализовать эту задачу.

import java.util.PriorityQueue;

public class OrderQueue
{
  PriorityQueue<Order> list = new PriorityQueue<>();

  public void add(Order order)
  {
    list.offer(order);
  }

  public Order get()
  {
    return list.isEmpty() ? null : list.poll();
  }

  public static void main(String[] args)
  {
    OrderQueue oq = new OrderQueue();
    oq.add(new Order(9000));
    oq.add(new Order(19000));
    oq.add(new Order(39000));
    oq.add(new Order(9001));
    oq.add(new Order(19001));
    oq.add(new Order(39001));

    while (!oq.list.isEmpty())
      System.out.println(oq.get());
  }
}

class Order implements Comparable<Order>
{
  private double sum;//  - сумма заказа

  public int getNum()
  {
    return num;
  }

  private int num;//  - номер заказа

  static int n_order = 1;

  public Order(double sum) // - для номера заказа создать систему автонумерации, начиная с 1
  {
    this.sum = sum;
    num = n_order;
    n_order++;
  }

  public int getLevel()
  {
    if (sum <= 10_000)
      return 3;
    else if (sum <= 20_000)
      return 2;
    else
      return 1;
  }

  @Override
  public String toString()
  {
    return getLevel() + " " + "sum=" + sum + ", num=" + num;
  }

  public double getSum()
  {
    return sum;
  }

  @Override
  public int compareTo(Order o)
  {
    if (getLevel() == o.getLevel())
      return Integer.compare(getNum(), o.getNum());
    else
      return Integer.compare(getLevel(), o.getLevel());
  }

}