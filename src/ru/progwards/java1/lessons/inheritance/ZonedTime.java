package ru.progwards.java1.lessons.inheritance;

/**
 * Автор: Фёдоров Александр
 * Дата:  21.11.2022  12:03
 */
public class ZonedTime extends Time {
    TimeZone zone;

    public ZonedTime(int hours, int minutes, int seconds, TimeZone zone) {
        super(hours, minutes, seconds);
        this.zone = zone;
    }

    public ZonedTime(int hours, int minutes, int seconds) {
        super(hours, minutes, seconds);
    }

    @Override
    public TimeZone getTimeZone() {
        return zone;
    }

    @Override
    public int toSeconds() {
        int zoneSec = 0;
        if (zone != null) {
            zoneSec = zone.hours * 3600 + zone.minutes * 60;
        }
        return super.toSeconds() + zoneSec;
    }

    @Override
    public int secondsBetween(Time time) {
        return Math.abs(toSeconds() - time.toSeconds());
    }

    public static void main(String[] args) {
        ZonedTime zt1 = new ZonedTime(2, 8, 53, new TimeZone(-1));
        ZonedTime zt2 = new ZonedTime(18, 1, 20, new TimeZone(-1));
        System.out.println(zt1.secondsBetween(zt2));  //49947

        ZonedTime zt3 = new ZonedTime(10, 3, 38, new TimeZone(-1, 53));
        Time t1 = new Time(16, 32, 24);
        System.out.println(zt3.secondsBetween(t1));  //22906
    }
}