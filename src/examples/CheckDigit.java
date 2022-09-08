package examples;

/**
 * Автор: Фёдоров Александр
 * Дата:  15.07.2022  10:38
 */
public class CheckDigit {
    public static boolean containsDigit(int number, int digit) {
        if (number == digit)
            return true;

        while (number != 0) {
            if (number % 10 == digit)
                return true;
            else
                number /= 10;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(containsDigit(0, 0));
    }
}