package ru.progwards.java1.lessons.compare_if_cycles;

public class CyclesGoldenFibo
{
  public static boolean containsDigit(int number, int digit)
//   будет возвращать true, если число number содержит цифру digit.
  {
    if (number == digit) return true;

    int n = number;
    while (n > 0) {
      if (digit == (n % 10)) return true;
      n = n / 10;
    }

    return false;
  }

 /*
 Далее в функции public static void main(String[] args) вывести на консоль, используя цикл,
 15 первых чисел Фибоначчи. После этого, используя вложенные циклы, определить:
 есть ли среди треугольников, длины сторон которых являются натуральными числами не превышающими 100,
  Золотые треугольники. И если есть, вывести на консоль длины основания и рёбер этих треугольников.
  Если всё получилось, посмотрите на консоль и сделайте вывод: есть ли связь между числами
  Фибоначчи и Золотыми треугольниками.
*/
  public static void main(String[] args)
  {
    String strfibo="Числа Фибоначчи - ";
    for (int i=1; i<=15; i++)
      strfibo = strfibo + fiboNumber(i) + " ";
    System.out.println(strfibo+"\n");

    for (int i=1; i<=100; i++)
      for (int j=1; j<=100; j++)
        if(isGoldenTriangle(j,j,i))
          System.out.println("основание - "+i+"   рёбра - "+j);
  }
/*
  Теперь проведём исследование. Есть ли у Золотого треугольника связь с числами Фибоначчи.
  Золотой треугольник это равнобедренный треугольник у которого ребро относится к основанию
  как 1.61803 (приблизительно), то есть по правилу Золотого сечения. Числа Фибоначчи это ряд
  чисел 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ... в котором первые два элемента равны 1,
  а каждый следующий равен сумме двух предыдущих.

  Для проведения этого исследования нам придётся решить две задачи.
*/
  public static int fiboNumber(int n)
//  будет возвращать n-ое число Фибоначчи (нумерация начинается с 1,
//  то есть при n = 3 должно вернуться число Фибоначчи 2, а при n = 10 число 55).
  {
    if (n < 3) return 1;

    int f0 = 1, f1 = 1, f2 = 0;

    for (int i = 3; i <= n; i++)
    {
      f2 = f1 + f0;
      f0 = f1;
      f1 = f2;
    }
    return f2;
  }

  static final double V1 = 1.61703;
  static final double V2 = 1.61903;

  public static boolean isGoldenTriangle(int a, int b, int c)
//   будет возвращать true, если треугольник со сторонами a, b, c является Золотым.
//   Определим критерии. Он должен быть равнобедренным и отношение ребра к основанию
//   должно лежать между значениями 1.61703 и 1.61903.
  {
    double ac = (double) a/c;
    double ab = (double) a/b;
    double ba = (double) b/a;

    return
      TriangleInfo.isIsoscelesTriangle(a, b, c)         &&
      (
        ((a == b) && (V1 <= ac) && (V2 >= ac))    ||
        ((a == c) && (V1 <= ab) && (V2 >= ab))    ||
        ((c == b) && (V1 <= ba) && (V2 >= ba))
      );
  }

}
