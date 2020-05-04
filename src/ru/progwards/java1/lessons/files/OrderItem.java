package ru.progwards.java1.lessons.files;

public class OrderItem
{
  public OrderItem(){}

  public OrderItem(String googsName, int count, double price)
  {
    this.googsName = googsName;
    this.count = count;
    this.price = price;
  }

  public String googsName;  //  наименование товара
  public int count; // -количество
  public double price; // цена за  единицу

  public double getSum()
  {
    return count * price;
  }

}
