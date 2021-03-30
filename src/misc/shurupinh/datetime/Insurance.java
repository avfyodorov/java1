package misc.shurupinh.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Insurance {
    public static enum FormatStyle {SHORT, LONG, FULL}

    private ZonedDateTime start;
    private Duration duration;


    public Insurance(ZonedDateTime start) {
        this.start = start;
    }

    public Insurance(String strStart, FormatStyle style) {
        switch (style) {
            // LocalDate.parse(text, formatter)
            case SHORT:
                LocalDate ld = LocalDate.parse(strStart);
                start = ld.atStartOfDay(ZoneId.systemDefault());
                break;
            case LONG:
                LocalDateTime ldt = LocalDateTime.parse(strStart);
                start = ldt.atZone(ZoneId.systemDefault());
                break;
            case FULL:
                ZonedDateTime zdt = ZonedDateTime.parse(strStart, DateTimeFormatter.ISO_ZONED_DATE_TIME);
                start = zdt;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + style);
        }
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setDuration(ZonedDateTime expiration) {
        duration = Duration.between(start, expiration);
    }

    public void setDuration(int months, int days, int hours) {
        var end = start.plusMonths(months).plusDays(days).plusHours(hours);
        duration = Duration.between(start, end);
    }

    public void setDuration(String strDuration, FormatStyle style) {
        switch (style) {

            case SHORT:
                duration = Duration.ofMillis(Long.valueOf(strDuration));
                break;
            case LONG:
                var ld = LocalDateTime.parse(strDuration);
                int m = ld.getYear() * 12 + ld.getMonthValue();
                int d = ld.getDayOfMonth();
                int h = ld.getHour();
                setDuration(m, d, h);
                break;
            case FULL:
                duration = Duration.parse(strDuration);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + style);
        }
    }

    public boolean checkValid(ZonedDateTime dateTime) {
        if (duration == null) {
            if (start.isBefore(ZonedDateTime.now())) return true;
            else return false;
        }

        ZonedDateTime end = start.plusNanos(duration.toNanos());

        if (dateTime.isAfter(start) && dateTime.isBefore(end)) return true;
        return false;
    }

    public String toString() {
        String valid = checkValid(ZonedDateTime.now()) ? " is valid" : " is not valid";
        return "Insurance issued on " + start + valid;
    }


    public static void main(String[] args) {
        Insurance inS = new Insurance("2019-12-03", FormatStyle.SHORT);
        Insurance inL = new Insurance("2019-12-03T10:15:30", FormatStyle.LONG);
        Insurance inF = new Insurance("2019-12-03T10:15:30+01:00[Europe/Paris]", FormatStyle.FULL);

        System.out.println(inS.toString());

        inS.setDuration("PT100H", FormatStyle.FULL);
        inL.setDuration("0000-10-03T10:15:30", FormatStyle.LONG);
        inF.setDuration("12960000000", FormatStyle.SHORT);
        System.out.println(inF.duration.toString());
        System.out.println(inF.start.toString());

        System.out.println(inS.toString());
        System.out.println(inL.toString());
        System.out.println(inF.toString());

    }
}