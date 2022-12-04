package ru.progwards.java1.lessons.inheritance;

/**
 * Автор: Фёдоров Александр
 * Дата:  21.11.2022  11:55
 */
public class TimeZone {
    int hours;
    int minutes;

    public TimeZone(int hours) {
        this.hours = hours;
        minutes=0;
    }

    public TimeZone(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }
}
