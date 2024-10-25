package ru.progwards.java1.lessons.date;

/**
 * Автор: Фёдоров Александр
 * Дата:  19.03.2022  12:01
 */

import java.util.*;

public class DateDiff {

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
      Calendar calendar = new GregorianCalendar(2031, Calendar.DECEMBER, 25, 2, 50, 55);
      Date d11 = calendar.getTime();
      Date d111 = new Date(d11.getTime() + 942);
      //System.out.println(d111);
      //23 июня 2003 года, 05:36:09.901
      Calendar calendar1 = new GregorianCalendar(2003, Calendar.JUNE, 23, 5, 36, 9);
      Date d21 = calendar1.getTime();
      Date d211 = new Date(d21.getTime() + 901);
      //System.out.println(d211);

      //timeBetween(d211, d111);

   }
}

