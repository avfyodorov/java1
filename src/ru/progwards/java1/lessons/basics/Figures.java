package ru.progwards.java1.lessons.basics;

/**
 * Автор: Фёдоров Александр
 * Дата:  12.02.2022  12:03
 */

public class Figures {
   static Double Pi = 3.14;

   public static double circle(double d) {
      // в условиях очень странная формула площади круга... площадь круга с радиусом R по формуле S = 4πR2
      // я все-таки склоняюсь к S = πR2 или S = (1/4)πD2, но с моей формулой робот говорит неправильно.
//      return Pi * d * d ;
      return Pi * Math.pow(d,2.0);
   }

   public static double square(double n) {
      return n * n;
   }

   public static Double triangle(double k) {
      double pp = k * 3/ 2.0;
      // S = √(p * (p-a) * (p-b) * (p-c))
      return Math.sqrt(pp * (pp - k) * (pp - k) * (pp - k));
   }

   public static double squareVsTraiange(double p) {
      return square(p)/triangle(p);
   }
   public static double squareVsCircle(double p){
      return square(p)/circle(p);
   }
   public static double triangleVsCircle(double p){
      return triangle(p)/circle(p);
   }
   public static void main(String[] args) {
        double x=4.0;
//      System.out.println(circle(29.533210012392157));
      System.out.println(circle(99.20895562570834));
        System.out.println(square(x));
        System.out.println(triangle(26.941684955104225));
        System.out.println(squareVsTraiange(x));
        System.out.println(squareVsCircle(82.08311743154493));
//      System.out.println(triangleVsCircle(97.7974790042695));
      System.out.println(triangleVsCircle(48.00241670946412));

   }
}
/*
Задача 3. Класс Figures: не пройдено, оценка: 21.0
Комментарий:
ERROR: Тест "Метод circle(double d)" не пройден. Метод возвращает неверное значение.
Передан параметр: 99.20895562570834. Возвращено: 30905.18899171943. Ожидалось: 30905.188991719428
OK: Тест "Метод square(double n)" пройден успешно.
OK: Тест "Метод triangle(double k)" пройден успешно.
OK: Тест "Метод squareVsTraiange(double p)" пройден успешно.
OK: Тест "Метод squareVsCircle(double p)" пройден успешно.
ERROR: Тест "Метод triangleVsCircle(double p)" не пройден. Метод возвращает неверное значение.
Передан параметр: 48.00241670946412. Возвращено: 0.13790213436057944. Ожидалось: 0.1379021343605794
По данной задаче в целом не зачет, решение возвращено на доработку. Задача выполнена на 65.63%
 */