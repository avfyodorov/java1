package examples;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class T16 {
   Date createDate()
//  , который возвращает дату - 28 февраля 1986, 0 часов, 0 минут, 0 секунд, 0 миллисекунд
   {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.set(1986, 1, 28);
      return calendar.getTime();
   }

   //output == 2020-01-01T12:00:00.000000001Z
   Instant createInstant()
//  , который возвращает Instant, соответствующий 1 января 2020 года, 15 часов
//  и одна наносекунда по Московскому времени
   {
      LocalDateTime ldt = LocalDateTime.of(2020, 1, 1, 15, 0, 0, 1);
      ZonedDateTime zdt = ldt.atZone(ZoneId.of("Europe/Moscow"));
      return zdt.toInstant();
   }

   //output == 2020-01-01T12:00:00.000000001Z
   Instant createInstant2()
//  , который возвращает Instant, соответствующий 1 января 2020 года, 15 часов
//  и одна наносекунда по Московскому времени
   {
      return ZonedDateTime
              .parse("2020-01-01T12:00:00.000000001Z")
//            .withZoneSameInstant(ZoneId.of("Europe/Moscow"))
              .toInstant();
   }

   public static void main(String[] args) {
      T16 t16 = new T16();
      System.out.println(t16.createDate());

      //output == 96
      LocalDateTime ldt1 = LocalDateTime.now();
      LocalDateTime ldt2 = ldt1.plusDays(4);
      Duration duration = Duration.between(ldt1, ldt2);
      System.out.println(duration.toHours());

      //output ==2019-05-05T22:24
      LocalDateTime ldt3 = LocalDateTime.of(2019, 05, 05, 22, 24);
      System.out.println(ldt3);

      //+03:00
      ZoneId zid1 = ZoneId.of("Europe/Moscow");
      System.out.println(zid1.getRules().getOffset(Instant.now()));

//output -== 2020-01-01T12:00:00.000000001Z
      System.out.println("zoned instans");
      System.out.println(t16.createInstant());
      System.out.println(t16.createInstant2());

//    Укажите формат для правильного вывода java.util.Date в виде "04.01.2020 15:58:37.346".
//    Формат укажите в двойных кавычках, как строка в Java - "aa bb"
      System.out.println(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.S").format(new Date()));

//    Укажите формат для правильного вывода java.util.Date в виде
//    "От'езд - суббота 04 января в 4PM".
//    Формат укажите в двойных кавычках, как строка в Java - "aa bb"
      System.out.println(new SimpleDateFormat("'От''езд' - EEEE dd MMMM 'в' ha").format(new Date()));

//    Укажите формат для правильного вывода java.time.Instant стандартного вида
//    "2020-01-04T13:21:42.173042400Z". Формат укажите в двойных кавычках, как строка в Java - "aa bb"
      System.out.println("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'");

      System.out.println(t16.parseZDT("01.01.2020 16:27:14.444 +0300 Moscow Standard Time"));

      System.out.println(t16.testZDT(""));
   }

   ZonedDateTime parseZDT(String str)
   //, который возвращает ZonedDateTime, парся строку вида
   // "01.01.2020 16:27:14.444 +0300 Moscow Standard Time"
   {
      DateTimeFormatter dtf =
              DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS Z zzzz").withLocale(Locale.US);
      Instant i9 = Instant.from(dtf.parse(str));
      return i9.atZone(ZoneId.of("Europe/Moscow"));
   }

   ZonedDateTime testZDT(String str) {
      str = "01.01.2020 16:27:14.444 +0300 Moscow Standard Time";
      DateTimeFormatter dateTimeFormatter =
              DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS Z zzzz").withLocale(Locale.US);
      ZonedDateTime zdt = ZonedDateTime.parse(str, dateTimeFormatter);
      return zdt;
   }

}
