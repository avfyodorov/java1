package ru.progwards.java2.lessons.reflection;

public class Person {
   private String name;
   private int age;
   private boolean sex;
   private double dbl;
   private long lng;
   private Object obj;

   public Person() {
      name = "иван";
      age = 25;
      sex = true;
      dbl = 3.62;
      lng = 8L;
      obj = "Объект";
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public boolean getSex() {
      return sex;
   }

   @Override
   public String toString() {
      return "Person{" +
              "name='" + name + '\'' +
              ", age=" + age +
              ", sex=" + sex +
              ", dbl=" + dbl +
              ", lng=" + lng +
              ", obj=" + obj +
              '}';
   }
}
