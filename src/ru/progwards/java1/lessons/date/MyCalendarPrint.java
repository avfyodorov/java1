package ru.progwards.java1.lessons.date;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Автор: Фёдоров Александр
 * Дата:  04.06.2023  11:14
 */
public class MyCalendarPrint {
    public static void printMonth(int month, int year) {
        String[] monthNames = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

//
        Calendar calendar = new GregorianCalendar(year, month - 1, 1);

//Напечатать год, месяц и название дней недели
        System.out.print(calendar.get(Calendar.YEAR) + " ");
        System.out.println(monthNames[calendar.get(Calendar.MONTH)]);
        System.out.println("ПН ВТ СР ЧТ ПТ СБ ВС");

//Узнать с какого дня недели начинается месяц и привести к русскому формату.
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = dayOfWeek == Calendar.SUNDAY ? 7 : dayOfWeek - 1;

//Добавить слева пробелы в соответствии с номером дня в неделе
        StringBuilder sb = new StringBuilder("   ".repeat(dayOfWeek - 1));

//Узнать количество дней в месяце
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//Собрать строку вида:   1  2  3  4  5  6  7  8  9 10 11 12 ...
        for (int i = 1; i <= lastDay; i++) {
            sb.append(i > 1 && i < 10 ? "  " : " ");
            sb.append(i);
        }

//Распечатать строку кусочками
        int i = 0;
        while (i < sb.length()) {
            System.out.println(sb.substring(i, Math.min(i + 21, sb.length())));
            i = i + 21;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printMonth(12, 2022);
        printMonth(1, 2023);
        printMonth(2, 2023);
        printMonth(5, 2023);

    }
}
