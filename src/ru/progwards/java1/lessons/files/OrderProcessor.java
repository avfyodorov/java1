package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//Информация о заказах поступает в виде файлов, которые лежат в под-папках разбитых по неделям,
// имена папок не имеют значения. Имя каждого файла имеет формат: AAA-999999-ZZZZ.csv
// где AAA - обязательные 3 символа shopId - идентификатор магазина,
// 999999 - обязательные 6 символов orderId - номер заказа,
// ZZZZ - обязательные 4 символа customerId - идентификатор покупателя,
// расширение файла - csv, например S02-P01X12-0012.csv: shopId=”S02”, orderId=”P01X12”, customerId=”0012”
//
//        Содержимое каждого файла имеет формат CSV (Comma Separated Values) со следующими данными
//
//        Наименование товара, количество, цена за единицу
//
//        Например:
//
//        Игрушка мягкая “Мишка”, 1, 1500
//        Пазл “Замок в лесу”, 2, 700
//        Книжка “Сказки Пушкина”, 1, 300

public class OrderProcessor
{
  String startPath;
  List<Order> orderList = new ArrayList<>();

  private void printOrders()
  {
    for (Order order : orderList)
    {
      System.out.println(order);
    }
  }

  public OrderProcessor(String startPath)
  //- инициализирует класс, с указанием начальной папки для хранения файлов
  {
    this.startPath = startPath;
  }

  int errors;

  public int loadOrders(LocalDate start, LocalDate finish, String shopId) throws IOException
  //- загружает заказы за указанный диапазон дат, с start до finish, обе даты включительно.
  // Если start == null, значит нет ограничения по дате слева,
  // если finish == null, значит нет ограничения по дате справа,
  // если shopId == null - грузим для всех магазинов
  // При наличии хотя бы одной ошибке в формате файла, файл полностью игнорируется,
  // т.е. Не поступает в обработку. Метод возвращает количество файлов с ошибками.
  // При этом, если в классе содержалась информация, ее надо удалить
  {
    errors = 0;
    orderList.clear();
//найти файлы, подходящие по имени
    String mask = String.format("glob:**/???-??????-%s.csv", (shopId == null ? "????" : shopId));
    PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(mask);
    Files.walkFileTree(Path.of(startPath), new SimpleFileVisitor<>()
      {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
        {
//отбор по имени
          if (pathMatcher.matches(file))
          {
            System.out.println(file);
//индекс магаза, допустимое время... загрузить и добавить
            Order order = Order.loadOrder(file, start, finish, shopId);
            if (order != null)
              orderList.add(order);
            else
              errors++;
          }
          return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc)
        {
          return FileVisitResult.CONTINUE;
        }
      }
    );

    return errors;
  }

  public List<Order> process(String shopId)
  //- выдать список заказов в порядке обработки (отсортированные по дате-времени),
  // для заданного магазина. Если shopId == null, то для всех
  {
    return null;
  }

  public Map<String, Double> statisticsByShop()
  //- выдать информацию по объему продаж по магазинам (отсортированную по ключам):
  // String - shopId, double - сумма стоимости всех проданных товаров в этом магазине
  {
    Map<String, Double> res = new TreeMap<>();
    for (Order order : orderList)
    {
      if (res.containsKey(order.shopId))
        res.put(order.shopId, res.get(order.shopId) + order.sum);
      else
        res.put(order.shopId, order.sum);
    }
    return res;
  }

  public Map<String, Double> statisticsByGoods()
  //- выдать информацию по объему продаж по товарам (отсортированную по ключам):
  // String - goodsName, double - сумма стоимости всех проданных товаров этого наименования
  {
    return null;
  }

  public Map<LocalDate, Double> statisticsByDay()
  //- выдать информацию по объему продаж по дням (отсортированную по ключам):
  // LocalDate - конкретный день, double - сумма стоимости всех проданных товаров в этот день
  {
    return null;
  }

  public static void main(String[] args) throws IOException
  {
    System.out.println("qyqy");
    OrderProcessor op = new OrderProcessor("c:/lib/java/yyy");
    op.loadOrders(null, null, null);
    op.printOrders();
  }
}

class Order
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
    } catch (IOException e)
    {
      return null;
    }
  }

  //        Игрушка мягкая “Мишка”, 1, 1500
//        Пазл “Замок в лесу”, 2, 700
//        Книжка “Сказки Пушкина”, 1, 300
  private void loadItems(Path path) throws IOException
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

  private Order(String shopId, String orderId, String customerId, LocalDateTime datetime)
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

class OrderItem
{
  public OrderItem(String googsName, int count, double price)
  {
    this.googsName = googsName;
    this.count = count;
    this.price = price;
  }

  public String googsName;  //  наименование товара
  public int count; // -количество
  public double price; // цена за  единицу
}