package ru.progwards.java1.lessons.date;

/**
 * Автор: Фёдоров Александр
 * Дата:  19.03.2022  12:03
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyDateDiff {
   public static void diffDate(int[] timeDiff, Calendar date1, Calendar date2){
      timeDiff[6] = (date2.get(Calendar.MILLISECOND) - date1.get(Calendar.MILLISECOND)) ; // ms
      if (timeDiff[6] < 0) {
         timeDiff[6] += 1000;
         timeDiff[5] = -1;
      }
      timeDiff[5] += (date2.get(Calendar.SECOND) - date1.get(Calendar.SECOND)) ; // sec
      if (timeDiff[5] < 0) {
         timeDiff[5] += 60;
         timeDiff[4] = -1;
      }
      timeDiff[4] += (date2.get(Calendar.MINUTE) - date1.get(Calendar.MINUTE)); // min
      if (timeDiff[4] < 0) {
         timeDiff[4] += 60;
         timeDiff[3] = -1;
      }
      timeDiff[3] += (date2.get(Calendar.HOUR_OF_DAY) - date1.get(Calendar.HOUR_OF_DAY)); // hour
      if (timeDiff[3] < 0) {
         timeDiff[3] += 24;
         timeDiff[2] = -1;
      }
      timeDiff[2] += (date2.get(Calendar.DATE) - date1.get(Calendar.DATE)) ; // day
      if (timeDiff[2] < 0) {
         timeDiff[2] += 30;
         timeDiff[1] = -1;
      }
      timeDiff[1] += (date2.get(Calendar.MONTH) - date1.get(Calendar.MONTH)) ; // month
      if (timeDiff[1] < 0) {
         timeDiff[1] += 12;
         timeDiff[0] = -1;
      }
      timeDiff[0] += (date2.get(Calendar.YEAR) - date1.get(Calendar.YEAR)) ; // year

   }
   public static int[] inTime(Date date1, Date date2, int shift) {
      Calendar cD1 = Calendar.getInstance();
      cD1.setTime(date1);
      Calendar cD2 = Calendar.getInstance();
      cD2.setTime(date2);
      if (shift == 1) { // вызов для дня рождения
         cD2.set(Calendar.YEAR, cD1.get(Calendar.YEAR));
         if (cD2.getTimeInMillis() > cD1.getTimeInMillis()){
            cD2.set(Calendar.YEAR, cD1.get(Calendar.YEAR) + 1);
         }
      }
      int[] timeDiff = new int[7]; // массив с данными по разнице дат, начиная с года и до мс
      if (cD2.getTimeInMillis() > cD1.getTimeInMillis()) { // смотрим какая дата больше для корректного вычитания
         diffDate(timeDiff, cD1, cD2 ); // заполнение массива
      }
      else{
         diffDate(timeDiff, cD2, cD1 );
      }

      return timeDiff;
   }

   public static void timeBetween(Date date1, Date date2) {
      int shift = 0; // здесь сдвиг не нужен
      int[] timeDiff = inTime(date1, date2, shift);
      System.out.print("Между date1 и date2 ");
      System.out.print(timeDiff[0] + " лет, " + timeDiff[1] + " месяцев, ");
      System.out.print(timeDiff[2] + " дней, " + timeDiff[3] + " часов, ");
      System.out.println(timeDiff[4] + " минут, " + timeDiff[5] + " секунд, " + timeDiff[6] + " миллисекунд");
   }

   public static void timeToBirthday(Date now, Date birthday) {
      int[] timeDiff;
      int shift = 1; // сдвиг для корректного вычисления месяцев, что из чего вычитать

      timeDiff = inTime(now, birthday, shift);
      System.out.print("До дня рождения ");
      System.out.print(timeDiff[1] + " месяцев, ");
      System.out.print(timeDiff[2] + " дней, " + timeDiff[3] + " часов, ");
      System.out.println(timeDiff[4] + " минут, " + timeDiff[5] + " секунд, " + timeDiff[6] + " миллисекунд");
   }

   public static void averageTime(Date[] events) {
      int shift = 0;
      int[] timeDiff = inTime(events[0], events[events.length - 1], 0);
      int[] timeConst = {0, 12, 30, 24, 60, 60, 1000};

      int k = 0;
      for (int i =0; i < timeDiff.length; i++ ){
         int tmp = (timeDiff[i] + k * timeConst[i]) / (events.length - 1);
         k = (timeDiff[i] + k * timeConst[i]) % (events.length - 1);
         timeDiff[i] = tmp;
      }

      System.out.print("Среднее время между событиями ");
      System.out.print(timeDiff[0] + " лет, " + timeDiff[1] + " месяцев, ");
      System.out.print(timeDiff[2] + " дней, " + timeDiff[3] + " часов, ");
      System.out.println(timeDiff[4] + " минут, " + timeDiff[5] + " секунд, " + timeDiff[6] + " миллисекунд");

   }

   public static void main(String[] args) {
      Date d1 = new Date(4444444444l);
      Date d2 = new Date(3333333333l);
      Date d3 = new Date();
      Date d4 = new Date(1660165200000l);

      //System.out.println(d4);
      //timeBetween(d1, d2);
      // 28 сентября 1973 года, 17:45:52.685
      Calendar cd1 = new GregorianCalendar(1973, 8, 28, 17, 45, 52);
      Date d_ = cd1.getTime();
      Date d5 = new Date(d_.getTime() + 685);
      // 14 января 1986 года, 14:15:43.691
      cd1 = new GregorianCalendar(1986, 0, 14, 14, 15, 43);
      d_ = cd1.getTime();
      Date d6 = new Date(d_.getTime() + 691);
      // 17 октября 1993 года, 09:13:39.692
      cd1 = new GregorianCalendar(1993, 9, 17, 9, 13, 39);
      d_ = cd1.getTime();
      Date d7 = new Date(d_.getTime() + 692);
      // 29 мая 2005 года, 17:05:20.692
      cd1 = new GregorianCalendar(2005, 4, 29, 17, 5, 20);
      d_ = cd1.getTime();
      Date d8 = new Date(d_.getTime() + 692);

      Date[] ev = {d5, d6, d7, d8};
        /*
        for (int i = 0; i < 3; i++) {
            System.out.println(ev[i]);
            System.out.println(ev[i + 1]);
            timeBetween(ev[i], ev[i + 1]);
        }
         */
      averageTime(ev);

      //23 октября 1985 года, 06:46:31.362
      cd1 = new GregorianCalendar(1985, Calendar.OCTOBER, 23, 6, 46, 31);
      d_ = cd1.getTime();
      Date d9 = new Date(d_.getTime()+362);
      //12 марта 2022 года, 13:24:12.110
      cd1 = new GregorianCalendar(2022, Calendar.MARCH, 12, 13, 24, 12);
      d_ = cd1.getTime();
      Date d10 = new Date(d_.getTime()+110);

      System.out.println(d9);
      System.out.println(d10);
      timeToBirthday(d9, d10);

      // 25 декабря 2031 года, 02:50:55.942
      Calendar calendar = new GregorianCalendar(2030, Calendar.JULY, 7,
              15, 02, 52);
      Date d11 = calendar.getTime();
      //System.out.println(d111);
      //23 июня 2003 года, 05:36:09.901
      Calendar calendar1 = new GregorianCalendar(2015, Calendar.MAY, 29,
              6, 43, 56);
      Date d21 = calendar1.getTime();

      timeBetween(d11, d21);
   }

}
/*

Ваш ответ не прошел автоматическую проверку.

Задача 1. Класс DateDiff: не пройдено, оценка: 0.0
Комментарий:
ERROR: Тест "Метод timeBetween(Date date1, Date date2)" не пройден. Метод, вызванный с параметрами,
соответствующими 07 июля 2030 года, 15:02:52.420 и
                 29 мая 2015 года, 06:43:56.180 вывел на консоль:
Между date1 и date2 15лет, 1 месяцев, 12 дней, 22 часов, 18 минут, 56 секунд, 240  миллисекунд

Ожидалось:
Между date1 и date2 15 лет, 1 месяцев, 8 дней, 8 часов, 18 минут, 56 секунд, 240 миллисекунд

---------------

ERROR: Тест "Метод timeToBirthday(Date birthday)" не пройден. Метод, вызванный с параметром,
соответствующим 16 июня 2021 года, 01:28:29.405,
дата и время выполнения 11 декабря 2023 года, 18:34:09.513 вывел на консоль:
До дня рождения 5 месяцев, 26 дней, 15 часов, 5 минут, 40 секунд, 108  миллисекунд

Ожидалось:
До дня рождения 6 месяцев, 4 дней, 6 часов, 54 минут, 19 секунд, 892 миллисекунд

-----------------

ERROR: Тест "Метод averageTime(Date[] events)" не пройден. Метод, вызванный с параметром в виде массива,
содержащего даты, соответствующие:
24 января 1970 года, 01:48:29.530
08 января 1980 года, 21:24:52.535
11 февраля 1991 года, 20:01:40.535
10 марта 2001 года, 17:19:21.535
 вывел на консоль:
Среднее время между событиями 10 лет, 4 месяцев, 17 дней, 21 часов, 10 минут, 17 секунд, 335  миллисекунд

Ожидалось:
Среднее время между событиями 10 лет, 4 месяцев, 16 дней, 13 часов, 10 минут, 17 секунд, 335 миллисекунд
По данной задаче в целом не зачет, решение возвращено на доработку. Задача выполнена на 0.00%

-------------------
Задача 2. Класс CalendarPrint: пройдено, оценка: 22.0
Комментарий:
OK: Тест "Метод printMonth(int month, int year)" пройден успешно.
По данной задаче в целом зачет, решение принято. Задача выполнена на 100.00%

=================
===================
================

        ERROR: Тест "Метод timeBetween(Date date1, Date date2)" не пройде
        Метод, вызванный с параметрами, соответствующими 03 марта 2076 года, 08:32:39.408
                                                       и 29 июня 2022 года, 14:00:11.132 вывел на консоль:
        Между date1 и date2 53 лет, 8 месяцев, 3 дней, 18 часов, 32 минут, 28 секунд, 276 миллисекунд

        Ожидалось:
        Между date1 и date2 53 лет, 8 месяцев, 2 дней, 18 часов, 32 минут, 28 секунд, 276 миллисекунд


        OK: Тест "Метод timeToBirthday(Date birthday)" пройден успешно.


        ERROR: Тест "Метод averageTime(Date[] events)" не пройден.
                Метод, вызванный с параметром в виде массива, содержащего даты, соответствующие:
        09 июля 1977 года, 19:10:26.536
        12 сентября 1983 года, 03:08:07.541
        22 июля 1995 года, 13:13:52.542
        12 ноября 2000 года, 19:59:24.542
        вывел на консоль:
        Среднее время между событиями 7 лет, 9 месяцев, 11 дней, 0 часов, 16 минут, 19 секунд, 335 миллисекунд

        Ожидалось:
        Среднее время между событиями 7 лет, 9 месяцев, 12 дней, 8 часов, 16 минут, 19 секунд, 335 миллисекунд
*/