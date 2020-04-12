package ru.progwards.java1.lessons.files;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
 public OrderProcessor(String startPath)
  //- инициализирует класс, с указанием начальной папки для хранения файлов
 {}

 public int loadOrders(LocalDate start, LocalDate finish, String shopId)
  //- загружает заказы за указанный диапазон дат, с start до finish, обе даты включительно.
  // Если start == null, значит нет ограничения по дате слева,
  // если finish == null, значит нет ограничения по дате справа,
  // если shopId == null - грузим для всех магазинов
  // При наличии хотя бы одной ошибке в формате файла, файл полностью игнорируется,
  // т.е. Не поступает в обработку. Метод возвращает количество файлов с ошибками.
  // При этом, если в классе содержалась информация, ее надо удалить
 {
   return 0;
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
   return null;
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

  public static void main(String[] args)
  {

  }
}

 class Order
 {
   public String shopId; //  идентификатор магазина
   public String orderId; //  идентификатор заказа
   public String customerId; //идентификатор покупателя
   public LocalDateTime datetime; // -дата- время заказа(из атрибутов файла-дата последнего изменения)
   public List<OrderItem> items;  //список позиций в заказе, отсортированный по наименованию товара
   public double sum; //сумма стоимости всех позиций  в заказе
 }

 class OrderItem
 {
   public String googsName;  //  наименование товара
   public int count; // -количество
   public double price; // цена за  единицу
 }