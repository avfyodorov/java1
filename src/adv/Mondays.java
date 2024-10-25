package adv;

/**
 * Автор: Фёдоров Александр
 * Дата:  25.10.2024  22:27
 */
public class Mondays {
    public static void main(String[] args) {
        //Завести массив констант -  сколько дней в каждом месяце
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //Создать массив (выделить память) под объекты типа месяц
        MonthData[] monthData = new MonthData[daysInMonth.length];

        //Заполнить массив. В качестве входного параметра берётся очередной элемент
        // (сколько дней в месяце) из массива констант.
        for (int i = 0; i < daysInMonth.length; i++) {
            monthData[i] = new MonthData(daysInMonth[i]);
        }

        //Печатать-посмотреть какой размер у каждого элемента.
        for (int i = 0; i < daysInMonth.length; i++) {
            System.out.println("В месяце " + (i + 1) + " оказалось " + monthData[i].days.length + " дней");
        }

    }
}

class MonthData {
    int[] days;

    public MonthData(int arrSize) {
        days = new int[arrSize];
    }
}


