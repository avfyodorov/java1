package examples;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class T16_1 {
   public static void main(String[] args) {
      DateTimeFormatter dateTimeFormatter =
              DateTimeFormatter.ofPattern("dd-MM-yyyy").withLocale(Locale.getDefault());
      LocalDate ld=LocalDate.parse("18-01-2021",dateTimeFormatter);
      System.out.println(ld);
   }
   ZonedDateTime testZDT(String str) {
      str = "01.01.2020 16:27:14.444 +0300 Moscow Standard Time";
      DateTimeFormatter dateTimeFormatter =
              DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS Z zzzz").withLocale(Locale.US);
      ZonedDateTime zdt = ZonedDateTime.parse(str, dateTimeFormatter);
      return zdt;
   }

}
