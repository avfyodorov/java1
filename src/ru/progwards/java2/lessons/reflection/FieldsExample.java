package ru.progwards.java2.lessons.reflection;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;

public class FieldsExample {
   public static void main(String[] args) {
      instance = new Person();
      System.out.println("До: " + instance);
      inspect("ru.progwards.java2.lessons.reflection.Person");
      System.out.println("После: " + instance);
   }

   static Person instance;
   static final String fieldType = "int,double,long,short";

   public static void inspect(String clazz) {
      try {
//получить данные по классу
         Class thisClass = Class.forName(clazz);
         String className = thisClass.getSimpleName();
         Field[] fields = thisClass.getDeclaredFields();
         Constructor[] constructors = thisClass.getDeclaredConstructors();
         Method[] methods = thisClass.getDeclaredMethods();

         for (Field f : fields) {
//тип поля как строка
            String type = f.getGenericType().toString();

//разрешить менять значение
            f.setAccessible(true);
//менять в зависимости от типа
            if (fieldType.indexOf(type) != -1)
               f.set(instance, 0);
            else if (type.equalsIgnoreCase("boolean"))
               f.set(instance, false);
            else
               f.set(instance, null);

//красиво печатать
            int index = type.indexOf(" ");
            if (index != -1) {
               type = type.substring(index + 1).trim();
            }
            System.out.println((Modifier.toString(f.getModifiers()) + " " +
                    type + " " + f.getName() + ";"));
         }
      } catch (ClassNotFoundException | IllegalAccessException e) {
         System.out.println(e.getMessage());
      }
   }

}
