package ru.progwards.java2.lessons.recursion.less;

/**
 * Автор: Фёдоров Александр
 * Дата:  01.08.2023  10:42
 */

/*
числовой ряд вида 1, 3, 6, 10, 15, 21, … .
N-е число ряда получается прибавлением n к предыдущему числу. Таким обра-
зом, чтобы получить второе число, мы увеличиваем первое (1) на 2; 1 + 2 = 3. Третье
число получается увеличением второго (3) на 3; 3 + 3 = 6 и т. д.
Такие ряды называются треугольными числами, потому что их можно наглядно
представить как число объектов, которые могут быть расставлены в форме треугольника.

 */
public class TriNumber {
    static int triangle(int n) {
        int total = 0;
        while (n > 0)           // Пока n равно 1 и более
        {
            total = total + n;  // Переменная total увеличивается на n (высоту столбца)
            --n;                // Уменьшение высоты столбца
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("Треугольное число 1 = " + triangle(1));
        System.out.println("Треугольное число 2 = " + triangle(2));
        System.out.println("Треугольное число 3 = " + triangle(3));
        System.out.println("Треугольное число 4 = " + triangle(4));
        System.out.println("Треугольное число 5 = " + triangle(5));


        System.out.println("Треугольное число 1 = " + triangleRecursion(1));
        System.out.println("Треугольное число 2 = " + triangleRecursion(2));
        System.out.println("Треугольное число 3 = " + triangleRecursion(3));
        System.out.println("Треугольное число 4 = " + triangleRecursion(4));
        System.out.println("Треугольное число 5 = " + triangleRecursion(5));

    }

    static int triangleRecursion(int n) {
        if (n == 1)
            return 1;
        else
            return n + triangleRecursion(n - 1);
    }
}
