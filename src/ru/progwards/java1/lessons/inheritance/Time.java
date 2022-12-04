package ru.progwards.java1.lessons.inheritance;

/**
 * Автор: Фёдоров Александр
 * Дата:  21.11.2022  11:52
 */
public class Time {
    int hours;
    int minutes;
    int seconds;

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        String h, m, s;
        final int tt = 10;
        if (hours < tt)
            h = "0" + hours;
        else
            h = Integer.toString(hours);
        if (minutes < tt)
            m = "0" + minutes;
        else
            m = Integer.toString(minutes);
        if (seconds < tt)
            s = "0" + seconds;
        else
            s = Integer.toString(seconds);
        return h + ":" + m + ":" + s;
    }

    public int toSeconds() {
        return hours * 60 * 60 + minutes * 60 + seconds;
    }

    public int secondsBetween(Time time) {
        return Math.abs(toSeconds() - time.toSeconds());
    }

    public TimeZone getTimeZone() {
        return null;
    }

    public static void main(String[] args) {

    }
}
