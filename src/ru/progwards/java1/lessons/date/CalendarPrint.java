package ru.progwards.java1.lessons.date;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarPrint {

    public static void printMonth(int month, int year) {
        // Проверка на допустимые значения месяца (1-12) и года
        if (month < 1 || month > 12 || year < 0) {
            System.out.println("Ошибка: Недопустимые значения месяца и/или года.");
            return;
        }
        // Определение названия месяца
        String[] monthNames = {"", "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        String monthName = monthNames[month];

        // Определение количества дней в месяце
        int daysInMonth;
        if (month == 2) {
            // Проверка на високосный год
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                daysInMonth = 29;
            } else {
                daysInMonth = 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            daysInMonth = 30;
        } else {
            daysInMonth = 31;
        }
        // Определение первого дня недели месяца (0 - воскресенье, 1 - понедельник, и т.д.)
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        int firstDayOfWeek = calendar.get(java.util.Calendar.DAY_OF_WEEK - 2);

// Вывод года и названия месяца
        System.out.println(year + " " + monthName);

        // Вывод дней недели
        System.out.println("пн вт ср чт пт сб вс");

        // Вывод календаря
        for (int i = 0; i < firstDayOfWeek; i++) {
            System.out.print("   "); // Вывод пробелов перед первым днем месяца
        }

        for (int i = 1; i <= daysInMonth; i++) {
            System.out.printf("%2d ", i); // Вывод чисел месяца с дополнительным пробелом спереди, если число однозначное
            if ((firstDayOfWeek + i) % 7 == 0) {
                System.out.println(); // Переход на новую строку после каждой недели
            }
        }

        System.out.println(); // Переход на новую строку после вывода календаря
    }

    public static void printMonthFAV(int month, int year) {
        String[] monthNames = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        String[] daysOfWeek = {"пн", "вт", "ср", "чт", "пт", "сб", "вс"};

        // Проверка на допустимые значения месяца (1-12) и года
        if (month < 1 || month > 12 || year < 0) {
            System.out.println("Ошибка: Недопустимые значения месяца и/или года.");
            return;
        }

//Сколько дней в месяце: перейти на следующий месяц и отнять 1 день, чтобы встать на последний день месяца
        Calendar calendar = new GregorianCalendar(year, month, 1);
//        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        int daysInMonth = calendar.get(Calendar.DAY_OF_MONTH);

        int mon = month - 1;
        System.out.println(year + " " + monthNames[mon] + " дней в месяце - " + daysInMonth);


    }

    public static void printFirstDays() {
        String[] monthNames = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        String[] daysOfWeek = {"пн", "вт", "ср", "чт", "пт", "сб", "вс"};
        int year = 2022;
        int month = 11;
        Calendar calendar = new GregorianCalendar(year, month, 1);
        do {
//Получить первый день недeли для 1-го числа месяца (1..7)
            int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//Русская система счисления Сдвинуть воскресенье в конец
            firstDayOfWeek = firstDayOfWeek == 1 ? 7 : firstDayOfWeek - 1;

//Добавить месяц
            calendar.add(Calendar.MONTH, 1);
//Сколько дней в месяце: отнять 1 день, чтобы встать на последний день месяца
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            int daysInMonth = calendar.get(Calendar.DAY_OF_MONTH);
            calendar.add(Calendar.DAY_OF_MONTH, 1);

            System.out.println(year + " " + monthNames[month] + " "
                    + daysOfWeek[firstDayOfWeek - 1]
                    + " дней в месяце - " + daysInMonth);

//Получить свежие значения года и месяца
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);

        } while (year < 2024);

    }

    public static void main(String[] args) {
        // Пример вызова метода printMonth() для месяца февраля 2022 года
        printMonth(1, 2023);
        printMonthFAV(1, 2023);
        printFirstDays();
    }
}