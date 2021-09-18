package ru.progwards.java1.lessons.datetime;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TempInsurance {
   public static void main(String[] args) {
      String strStart="2020-03-30";
      DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
      ZonedDateTime start;//=ZonedDateTime.parse(strStart, dtf);

      LocalDate ld = LocalDate.from(dtf.parse(strStart));
      start = ld.atStartOfDay(ZoneId.systemDefault());

      System.out.println(start);
   }
}
