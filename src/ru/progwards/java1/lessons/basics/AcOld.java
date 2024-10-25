package ru.progwards.java1.lessons.basics;

/**
 * Автор: Фёдоров Александр
 * Дата:  07.08.2024  22:45
 */
public class AcOld {
    static final double PI = 3.14; // Объявление константы свойствами класса

    public static Double volumeBallDouble(Double radius) {
        return (4.0 / 3.0) * PI * Math.pow(radius, 3); // Возвращение объема
    }

    public static Float volumeBallFloat(Float radius) {
        return (float) ((4.0f / 3.0f) * PI * Math.pow(radius, 3)); // Возвращение объема
    }

    public static Double calculateAccuracy(Double radius) {
        Double volumeDouble = volumeBallDouble(radius);
        Float volumeFloat = volumeBallFloat(radius.floatValue());
        return volumeDouble - volumeFloat;
    }

    public static void main(String[] args) {
        Double radius = 6371.2;
        Double volumeD = volumeBallDouble(radius);
        Float volumeF = volumeBallFloat(radius.floatValue());
        Double accuracy = calculateAccuracy(radius);

        System.out.println("Объем шара с радиусом " + radius + " км (Double): " + volumeD + " куб. км");
        System.out.println("Объем шара с радиусом " + radius + " км (Float): " + volumeF + " куб. км");
        System.out.println("Разница между Double и Float: " + accuracy + " куб. км");
    }
}
