package misc.twist328.recursion;

public class AsNumbersSum {

    private static final String EQUAll = " = ";
    private static final String SUMM = "+";

     /*Реализовать класс AsNumbersSum
     раскладывает параметр number, как всевозможные уникальные комбинации сумм натуральных чисел, например:
     5 = 4+1 = 3+2 = 3+1+1 = 2+2+1 = 2+1+1+1 = 1+1+1+1+1*/


    public static String showAllThePoss(int a, int b, String c) {
        return
                (a <= 0 ? "" :       // условие выхода из рекурсии
                        (b > a                                                         //  второе число больше
                                ? showAllThePoss(a, b - a, c + a + SUMM)        // все варианты второго числа
                                : EQUAll + c + a + SUMM + b                          // вариант в настоящем времени
                                + showAllThePoss(b - 1, 1, c + a + SUMM)   // плюс все возможности
                        )
                                + showAllThePoss(a - 1, b + 1, c)           // + ещё варианты, со вторым числом
                );
    }
    public static String putItAllSum(int number) {
        return number + showAllThePoss(number-1, 1, "");
    }


    public static void main(String[] args) {

        System.out.println(putItAllSum(5));
    }

}

