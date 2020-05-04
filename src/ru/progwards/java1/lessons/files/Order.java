package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Order
{
  // имена папок не имеют значения. Имя каждого файла имеет формат: AAA-999999-ZZZZ.csv
// где AAA - обязательные 3 символа shopId - идентификатор магазина,
// 999999 - обязательные 6 символов orderId - номер заказа,
// ZZZZ - обязательные 4 символа customerId - идентификатор покупателя,
// расширение файла - csv, например S02-P01X12-0012.csv: shopId=”S02”, orderId=”P01X12”, customerId=”0012”
  public static Order loadOrder(Path path, LocalDate start, LocalDate finish, String shopId)
  {
    try
    {
      String filename = path.getFileName().toString();
      FileTime time = (FileTime) Files.getAttribute(path, "basic:lastModifiedTime");
      LocalDateTime ldt = LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault());
      LocalDate localDate = LocalDate.ofInstant(time.toInstant(), ZoneId.systemDefault());

//      System.out.println("finish: "+finish+" filetime: "+localDate);
      if ((start == null || start.compareTo(localDate) <= 0) &&
        (finish == null || finish.compareTo(localDate) >= 0) &&
        (shopId == null || shopId.compareTo(filename.substring(0, 3)) == 0)
      )
      {
        Order order = new Order(filename.substring(0, 3), filename.substring(4, 10),
          filename.substring(11, 15), ldt);
        order.loadItems(path);
        return order;
      } else
        return null;
    } catch (Exception e)
    {
      return null;
    }
  }

  //        Игрушка мягкая “Мишка”, 1, 1500
//        Пазл “Замок в лесу”, 2, 700
//        Книжка “Сказки Пушкина”, 1, 300
  private void loadItems(Path path) throws IOException, NumberFormatException
  {
    sum = 0.0;
    List<String> lines = Files.readAllLines(path);
    for (String line : lines)
    {
      String[] item = line.split(",");

      String goog = item[0].trim();
      int count = Integer.parseInt(item[1].trim());
      double price = Double.parseDouble(item[2].trim());

      sum = sum + price * count;

      items.add(new OrderItem(goog, count, price));
    }
  }

  public Order(){}

  public Order(String shopId, String orderId, String customerId, LocalDateTime datetime)
  {
    this.shopId = shopId;
    this.orderId = orderId;
    this.customerId = customerId;
    this.datetime = datetime;
    items = new ArrayList<>();
  }

  public String shopId; //  идентификатор магазина
  public String orderId; //  идентификатор заказа
  public String customerId; //идентификатор покупателя
  public LocalDateTime datetime; // -дата- время заказа(из атрибутов файла-дата последнего изменения)
  public List<OrderItem> items;  //список позиций в заказе, отсортированный по наименованию товара
  public double sum; //сумма стоимости всех позиций  в заказе

  @Override
  public String toString()
  {
    return "Order{" +
      "shopId='" + shopId + '\'' +
      ", orderId='" + orderId + '\'' +
      ", customerId='" + customerId + '\'' +
      ", datetime=" + datetime +
      //", items=" + items +
      ", sum=" + sum +
      '}';
  }
}
