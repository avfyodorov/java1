package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

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

  private void printOrders(List<Order> list)
  {
    for (Order order : list)
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

  public int loadOrders(LocalDate start, LocalDate finish, String shopId) //throws IOException
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
    try
    {


//найти файлы, подходящие по имени
      String mask = String.format("glob:**/%s-??????-????.csv", (shopId == null ? "???" : shopId));
      PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(mask);
      Files.walkFileTree(Path.of(startPath), new SimpleFileVisitor<>()
        {
          @Override
          public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
          {
//отбор по имени
            if (pathMatcher.matches(file))
            {
//            System.out.println(file);
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
    } catch (IOException e)
    {
      throw new UncheckedIOException(e);
    }

  }

  public List<Order> process(String shopId)
  //- выдать список заказов в порядке обработки (отсортированные по дате-времени),
  // для заданного магазина. Если shopId == null, то для всех
  {
    TreeSet<Order> tree = new TreeSet<>(new Comparator<>()
    {
      @Override
      public int compare(Order o1, Order o2)
      {
        return o1.datetime.compareTo(o2.datetime);
      }
    });

    for (Order item : orderList)
    {
      if (shopId == null || item.shopId.compareTo(shopId) == 0)
        tree.add(item);
    }

    return new ArrayList<>(tree);
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
    Map<String, Double> res = new TreeMap<>();
    for (Order order : orderList)
    {
      for (OrderItem item : order.items)
      {
        if (res.containsKey(item.googsName))
          res.put(item.googsName, res.get(item.googsName) + item.getSum());
        else
          res.put(item.googsName, item.getSum());
      }
    }
    return res;
  }

  public Map<LocalDate, Double> statisticsByDay()
  //- выдать информацию по объему продаж по дням (отсортированную по ключам):
  // LocalDate - конкретный день, double - сумма стоимости всех проданных товаров в этот день
  {
    Map<LocalDate, Double> res = new TreeMap<>();
    for (Order order : orderList)
    {
      LocalDate localDate = LocalDate.from(order.datetime);
      if (res.containsKey(localDate))
        res.put(localDate, res.get(localDate) + order.sum);
      else
        res.put(localDate, order.sum);
    }
    return res;
  }

  public static void main(String[] args) throws IOException
  {
    OrderProcessor op = new OrderProcessor("c:/lib/java/yyy");

    LocalDate finish = LocalDate.of(2020, 5, 15);
    System.out.println("errors: " + op.loadOrders(null, finish, null));
    op.printOrders(op.orderList);

    System.out.println("----------------");
    op.printOrders(op.process(null));

    System.out.println("----------------");
    System.out.println("by shop:" + op.statisticsByShop());

    System.out.println("----------------");
    System.out.println("by goods:" + op.statisticsByGoods());

    System.out.println("----------------");
    System.out.println("by days:" + op.statisticsByDay());

  }
}

