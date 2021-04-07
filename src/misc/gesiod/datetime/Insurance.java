package misc.gesiod.datetime;


import java.time.*;
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
//      checkValid(this.start);
   }

   //установить дату-время начала действия страховки
//    SHORT соответствует ISO_LOCAL_DATE
//    LONG  - ISO_LOCAL_DATE_TIME
//    FULL - ISO_ZONED_DATE_TIME

   public Insurance(String strStart, FormatStyle style){
      switch (style){
         case SHORT:
//                LocalDate localDate = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(strStart));
            LocalDate localDate = LocalDate.parse(strStart, DateTimeFormatter.ISO_LOCAL_DATE);
            this.start = localDate.atStartOfDay(ZoneId.systemDefault());
            break;
         case LONG:
            LocalDateTime localDateTime = LocalDateTime.parse(strStart, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            this.start = localDateTime.atZone(ZoneId.systemDefault());
            break;
         case FULL:
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(strStart, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            this.start = zonedDateTime;
            break;
         default:
            throw new IllegalStateException("Unexpected value: " + style);
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
   public void setDuration(int months, int days, int hours){
      this.duration = Duration.ofHours(hours + 24 * (days + 30 * months));
   }

   //1.6 - установить продолжительность действия страховки
   public void setDuration(String strDuration, FormatStyle style){
      ZonedDateTime zdtForDuration;
      switch (style){
         case SHORT:
//                LocalDate localDate = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(strStart));
            LocalDate localDate = LocalDate.parse(strDuration, DateTimeFormatter.ISO_LOCAL_DATE);
            zdtForDuration = localDate.atStartOfDay(ZoneId.systemDefault());
            break;
         case LONG:
            LocalDateTime localDateTime = LocalDateTime.parse(strDuration, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            zdtForDuration = localDateTime.atZone(ZoneId.systemDefault());
            break;
         case FULL:
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(strDuration, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            zdtForDuration = zonedDateTime;
            break;
         default:
            throw new IllegalStateException("Unexpected value: " + style);
      }
      this.duration = Duration.between(start, zdtForDuration);
   }

   //методы возврата информации:
   //проверить действительна ли страховка на указанную дату-время. Если продолжительность не задана считать страховку бессрочной
   public boolean checkValid(ZonedDateTime dateTime){
//        isValid = false;
//        if (duration == null){
//            isValid = true;
//            return isValid;
//        }
//        ZonedDateTime finishInsurance = start.plusSeconds(duration.getSeconds());
//        if (start.getSecond() <= dateTime.getSecond() && dateTime.getSecond() <= finishInsurance.getSecond()){
//            isValid = true;
//        }
//        return isValid;

      if (start.isAfter(dateTime))
         return isValid = false;
      if (duration == null)
         return isValid = true;

      return isValid = duration.compareTo(Duration.between(start, dateTime)) > 0 ? true : false;

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
   public static void main(String[] args) throws InterruptedException {
      ZonedDateTime zdf = ZonedDateTime.from(
              DateTimeFormatter.ISO_ZONED_DATE_TIME.parse("2021-03-31T11:54:11.165802+03:00[Europe/Moscow]"));
      System.out.println(zdf);
      Insurance in = new Insurance(zdf);
      in.setDuration(Duration.ofDays(1));
      System.out.println(in);
   }
/*
   public static void main(String[] args) {
      Instant instant1 = Instant.now();
      ZonedDateTime zdt1 = instant1.atZone(ZoneId.of("Europe/Moscow"));
      Instant instant2 = instant1.plusSeconds(180);
      ZonedDateTime zdt2 = instant2.atZone(ZoneId.of("Europe/Moscow"));
      System.out.println(zdt1.toString());
      Insurance test = new Insurance(zdt1);
//        test.setDuration(zdt2);
      test.setDuration(1, 3, 1);
      System.out.println( test.getDuration().toMillis());
      System.out.println(test.checkValid(zdt2));
      System.out.println(test.toString());
      System.out.println();
      Insurance insurance = new Insurance("2020-04-01", FormatStyle.SHORT);
      System.out.println(insurance);
   }
   */
}
