package ru.progwards.java1.lessons;

/**
 * Автор: Фёдоров Александр
 * Дата:  16.02.2022  12:22
 */
public abstract class Animal {
   String name;

   public Animal(String name) {
      this.name = name;
   }

   public abstract String kind();

   public abstract String say();

   public String toString() {
      return "Это " + kind() + " " + this.name;
   }
}

class Cow extends Animal {
   public Cow(String name) {
      super(name);
   }

   @Override
   public String kind() {
      return "корова";
   }

   @Override
   public String say() {
      return "мууууууу";
   }
}