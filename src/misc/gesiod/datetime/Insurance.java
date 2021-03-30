package misc.gesiod.datetime;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Insurance {
   //дата-время начала действия страховки
   private ZonedDateTime start;
   //продолжительность действия
   private Duration duration;

   private boolean isValid;

   //стили формата даты-времени
   public static enum FormatStyle {SHORT, LONG, FULL}

   //Реализовать конструкторы:
   //установить дату-время начала действия страховки
   public Insurance(ZonedDateTime start){
      this.start = start;
      checkValid(start);
   }

   //установить дату-время начала действия страховки
//    SHORT соответствует ISO_LOCAL_DATE
//    LONG  - ISO_LOCAL_DATE_TIME
//    FULL - ISO_ZONED_DATE_TIME

   public Insurance(String strStart, FormatStyle style){
      Instant instant = null;
      switch (style){
         case SHORT:
            instant = Instant.from((DateTimeFormatter.ISO_LOCAL_DATE.parse(strStart)));
            start = instant.atZone(ZoneId.systemDefault());
            break;
         case LONG:
            instant = Instant.from((DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(strStart)));
            start = instant.atZone(ZoneId.systemDefault());
            break;
         case FULL:
            instant = Instant.from((DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(strStart)));
            start = instant.atZone(ZoneId.systemDefault());
            break;
      }
      checkValid(start);
   }
   //Для вариантов, когда не задан явно часовой пояс использовать таковой по умолчанию.

   //1.3 Реализовать методы, устанавливающие продолжительность действия страховки:
   //установить продолжительность действия страховки
   public void setDuration(Duration duration){
      this.duration = duration;
   }

   //1.4 установить продолжительность действия страховки, задав дату-время окончания
   public void setDuration(ZonedDateTime expiration){
      this.duration = Duration.between(start, expiration);
   }
   //1.5 -  установить продолжительность действия страховки, задав целыми числами количество месяцев, дней и часов
   public void setDuration(int months, int days, int hours){}

   //1.6 - установить продолжительность действия страховки
   public void setDuration(String strDuration, FormatStyle style){}

   //методы возврата информации:
   //проверить действительна ли страховка на указанную дату-время. Если продолжительность не задана считать страховку бессрочной
   public boolean checkValid(ZonedDateTime dateTime){
      isValid = false;
      if (duration == null){
         isValid = true;
         return isValid;
      }
      ZonedDateTime finishInsurance = start.plusSeconds(duration.getSeconds());
      if (start.getSecond() <= dateTime.getSecond() && dateTime.getSecond() <= finishInsurance.getSecond()){
         isValid = true;
      }
      return isValid;
   }

   //вернуть строку формата "Insurance issued on " + start + validStr, где validStr = " is valid",
   // если страховка действительна на данный момент и " is not valid", если она недействительна.
   public String toString(){
      String s = isValid ? " is valid" : " is not valid";
      return "Insurance issued on " + start.toString() + s;
   }

   public ZonedDateTime getStart() {
      return start;
   }

   public Duration getDuration() {
      return duration;
   }

   public static void main(String[] args) {
      Instant instant1 = Instant.now();
      ZonedDateTime zdt1 = instant1.atZone(ZoneId.of("Europe/Moscow"));
      Instant instant2 = instant1.plusSeconds(180);
      ZonedDateTime zdt2 = instant2.atZone(ZoneId.of("Europe/Moscow"));
      System.out.println(zdt1.toString());
      Insurance test = new Insurance(zdt1);
      test.setDuration(zdt2);
      System.out.println( test.getDuration().toMillis());
      System.out.println(test.checkValid(zdt2));
      System.out.println(test.toString());
      System.out.println();


   }
}

