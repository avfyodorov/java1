package ru.progwards.java1.lessons.maps;

//Информация по продажам
//
//        Во входном файле находятся данные в CSV формате,
//        CSV - Comma Separated Values, значения разделенные запятыми.
//        Каждая строка - данные об одной покупке. Входные данные
//
//        ФИ покупателя, наименование товара, количество, сумма
//
//        String, String, int, double
//
//        Пример
//
//        Иванов Сергей, iPhone 10X, 2, 150000
//        Петрова Анна, наушники JBL, 2, 7000
//        Иванов Сергей, чехол для iPhone, 1, 1000
//        Петрова Анна, пакет пластиковый, 1, 3
//        Радж Кумар, батарейка ААА, 1, 150
//        Михаил Цикло, iPhone 10X, 1, 75000
//        Радж Кумар, пакет пластиковый, 1, 3
//        Михаил Цикло, пакет пластиковый, 1, 3
//        Иванов Сергей, стекло защитное, 1, 1000
//        Василий Пупкин, спички, 10, 10
//
//        …
//
//
//        3.1 Реализовать метод public int loadOrders(String fileName)
//        - вернуть количество успешно загруженных строк.
//        Если в строке более или менее 4-x полей, или количество и сумма не преобразуются в числа
//        - эту строку не загружаем.
//
//        3.2 Реализовать метод public Map<String, Double> getGoods()
//        - вернуть список товаров, отсортированный по наименованию товара.
//        В String - наименование товара, в Double - общая сумма продаж по товару
//
//        3.3 Реализовать метод
//        public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers()
//        - вернуть список покупателей, отсортированный по алфавиту.
//        В String  - ФИ, в Double - сумма всех покупок покупателя, в Integer - количество покупок
//
//        3.4 Протестировать на данных из примера выше
//
//
//
//        wiki.test.tokens wiki.test.tokens
//
//
//        wiki.train.tokens wiki.train.tokens

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SalesInfo
{
  List<Order> list = new ArrayList<>();

  public int loadOrders(String fileName)// throws FileNotFoundException
  {
    try (Scanner in = new Scanner(new File(fileName)))
    {
      while (in.hasNextLine())
      {
        Order order = Order.parseOrder(in.nextLine());
        if (order != null)
          list.add(order);

      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println(e.getMessage());
    }
    return list.size();
  }

  public Map<String, Double> getGoods()
//        - вернуть список товаров, отсортированный по наименованию товара.
//        В String - наименование товара, в Double - общая сумма продаж по товару
  {
    Map<String, Double> res = new TreeMap<>();

    for (Order order : list)
      if (res.containsKey(order.item))
        res.put(order.item, res.get(order.item) + order.sum);
      else
        res.put(order.item, order.sum);

    return res;
  }

  public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers()
//        - вернуть список покупателей, отсортированный по алфавиту.
//        В String  - ФИ, в Double - сумма всех покупок покупателя,
//        в Integer - количество покупок
  {
    Map<String, AbstractMap.SimpleEntry<Double, Integer>> res = new TreeMap<>();

    for (Order order : list)
      if (res.containsKey(order.fio))
      {
        AbstractMap.SimpleEntry<Double, Integer> se = res.get(order.fio);
        double d = se.getKey() + order.sum;
        int i = se.getValue() + order.kol;
        res.put(order.fio, new AbstractMap.SimpleEntry<>(d, i));

      }
      else
        res.put(order.fio, new AbstractMap.SimpleEntry<>(order.sum, order.kol));

      return res;
  }

  public static void main(String[] args) throws FileNotFoundException
  {
    SalesInfo si = new SalesInfo();
    System.out.println(si.loadOrders("mymmy.txt"));
    System.out.println(si.getGoods());
    System.out.println(si.getCustomers());
  }
}

class Order
{
  public static Order parseOrder(String record)
  {
    try
    {
      String[] s = record.split(",");
      if (s.length != 4)
        return null;

      String item = s[1].trim();
      String fio = s[0].trim();
      int kol = Integer.parseInt(s[2].trim());
      double sum = Double.parseDouble(s[3].trim());

      return new Order(fio, item, kol, sum);
    } catch (NumberFormatException e)
    {
      return null;
    }
  }

  private Order(String fio, String item, int kol, double sum)
  {
    this.fio = fio;
    this.item = item;
    this.kol = kol;
    this.sum = sum;
  }

  String fio;
  String item;
  int kol;
  double sum;
}