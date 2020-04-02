package ru.progwards.java1.lessons.datetime;

//Класс должен проверять валидность страховок, например для выезжающих за рубеж.
// Каждая страховка имеет дату-время начала, и продолжительность.


import java.time.*;
import java.time.format.DateTimeFormatter;


public class Insurance
{
  public Insurance(ZonedDateTime start) //- установить дату-время начала действия страховки.
  {
    this.start = start;
  }

  public Insurance(String strStart, FormatStyle style)
  // - установить дату-время начала действия страховки
//  SHORT соответствует ISO_LOCAL_DATE
//  LONG  - ISO_LOCAL_DATE_TIME
//  FULL - ISO_ZONED_DATE_TIME
//  Для вариантов, когда не задан явно часовой пояс использовать таковой по умолчанию.
  {
    this.style = style;

    if (style == FormatStyle.SHORT)
    {
      LocalDate ld = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(strStart));
      start = ld.atStartOfDay(ZoneId.systemDefault());
    } else if (style == FormatStyle.LONG)
    {
      LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(strStart));
      start = localDateTime.atZone(ZoneId.systemDefault());
    } else
      start = ZonedDateTime.from(DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(strStart));

  }

  private FormatStyle style;
  private ZonedDateTime start; // - дата-время начала действия страховки.
  private Duration duration; // - продолжительность действия.

  public void setDuration(Duration duration)
  // - установить продолжительность действия страховки.
  {
    this.duration = duration;
  }

  public void setDuration(ZonedDateTime expiration)
  //- установить продолжительность действия страховки, задав дату-время окончания.
  {
    setDuration(Duration.between(start, expiration));
  }

  public void setDuration(int months, int days, int hours)
  //- установить продолжительность действия страховки,
  // задав целыми числами количество месяцев, дней и часов.
  {
    setDuration(start.plusMonths(months).plusDays(days).plusHours(hours));
  }

  public void setDuration(String strDuration, FormatStyle style)
  //- установить продолжительность действия страховки
  //SHORT - целое число миллисекунд (тип long)
  //LONG  - ISO_LOCAL_DATE_TIME - как период, например “0000-06-03T10:00:00” означает,
  // что продолжительность действия страховки 0 лет, 6 месяцев, 3 дня 10 часов.
  // FULL - стандартный формат Duration, который получается через toString()
  {
    if (style == FormatStyle.SHORT)
      duration = Duration.ofMillis(Long.parseLong(strDuration));
    else if (style == FormatStyle.LONG)
    {
      LocalDateTime v = LocalDateTime.parse(strDuration, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
      ZonedDateTime z = start.plusYears(v.getYear()).
              plusMonths(v.getMonthValue()).
              plusDays(v.getDayOfMonth()).
              plusHours(v.getHour()).
              plusMinutes(v.getMinute()).
              plusSeconds(v.getSecond());
      duration = Duration.between(start, z);

    } else
      duration = Duration.parse(strDuration);
  }

  public boolean checkValid(ZonedDateTime dateTime)
  //- проверить действительна ли страховка на указанную дату-время.
  // Если продолжительность не задана считать страховку бессрочной.
  {
    if (start.isAfter(dateTime))
      return false;
    if (duration == null)
      return true;

    return duration.compareTo(Duration.between(start, dateTime)) > 0 ? true : false;

  }

  public String toString()
  //- вернуть строку формата "Insurance issued on " + start + validStr,
  // где validStr = " is valid", если страховка действительна на данный момент
  // и " is not valid", если она недействительна.
  {
    String valid = checkValid(ZonedDateTime.now()) ? " is valid" : " is not valid";
    return "Insurance issued on " + start + valid;
  }

  public static void main(String[] args) throws InterruptedException
  {
    Insurance insurance = new Insurance("2020-04-03T18:03:30.7495944+03:00[Europe/Moscow]", FormatStyle.FULL);
    insurance.setDuration("1", FormatStyle.SHORT);

    System.out.println(insurance);

//    Insurance insurance = new Insurance(ZonedDateTime.now());
//    System.out.println(insurance);
//
//    insurance.setDuration("PT1S", FormatStyle.FULL);
//    System.out.println(insurance);
//    Thread.sleep(1001);
//    System.out.println(insurance);
//
//    System.out.println(Duration.parse("PT73S"));
//
//    //Period duration1=Period.parse("0000-01-01T00:00:11");
//    LocalDateTime v = LocalDateTime.parse("0000-01-01T00:00:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//    ZonedDateTime z = insurance.start.plusYears(v.getYear()).plusMonths(v.getMonthValue()).
//            plusDays(v.getDayOfMonth()).
//            plusHours(v.getHour()).plusMinutes(v.getMinute()).plusSeconds(v.getSecond());
//    System.out.println(Duration.between(insurance.start, z));
//    insurance = new Insurance("2020-03-30T18:03:30.7495944+03:00[Europe/Moscow]", FormatStyle.FULL);
//    System.out.println(insurance);
//    insurance = new Insurance("2020-03-30T18:03:30.7495944", FormatStyle.LONG);
//    System.out.println(insurance);
//    insurance = new Insurance("2020-03-30", FormatStyle.SHORT);
//    System.out.println(insurance);

/*
2020-03-30T18:03:30.749594400+03:00[Europe/Moscow]
2020-03-30T18:13:25.4363104
2020-03-30
*/
//    ZonedDateTime zdf;
//    LocalDate ld = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse("2020-03-30"));
//    zdf = ld.atStartOfDay(ZoneId.systemDefault());
//    System.out.println(zdf);
//
//    LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse("2020-03-30T18:13:25.4363104"));
//    zdf = localDateTime.atZone(ZoneId.systemDefault());
//    System.out.println(zdf);
//
//    zdf = ZonedDateTime.from(DateTimeFormatter.ISO_ZONED_DATE_TIME.parse("2020-03-30T18:03:30.749594400+03:00[Europe/Moscow]"));
//    System.out.println(zdf);
//
  }
}
  enum FormatStyle
  {SHORT, LONG, FULL} //- стиль формата даты-времени
