package ru.progwards.java2.lessons.recursion;

        import java.time.Instant;

public class Goods {
   String name;// - наименование
   String number;// - артикул
   int available;// - количество на складе
   double price;// - цена
   Instant expired;// - срок годности

   public Goods(String name,String number,int available,double price,Instant expired) {
      this.name = name;
      this.number = number;
      this.available = available;
      this.price = price;
      this.expired = expired;
   }

   @Override
   public String toString() {
      return name + " " + number + " " + available + " " + price + " " + expired.getEpochSecond();
   }
}

