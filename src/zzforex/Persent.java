package zzforex;

/**
 * Автор: Фёдоров Александр
 * Дата:  30.07.2024  12:22
 */
public class Persent {
    public static void main(String[] args) {
        int days = 1;
        double sum = 100;
        double persent = 1.005;
        while ((sum = sum * persent) < 200) {
            days++;
        }
        System.out.println("Процент = " + persent + " Дней до удвоения - " + days);
    }
}
