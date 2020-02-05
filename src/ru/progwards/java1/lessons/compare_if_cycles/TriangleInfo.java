package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleInfo
{
  public static void main(String[] args)
  {
    System.out.println(isIsoscelesTriangle(4, 4, 15));
  }

  public static boolean isTriangle(int a, int b, int c)
//  возвращает true, если по данным трём сторонам (a, b, c) можно построить треугольник.
//  Из геометрии известно, что в треугольнике длина каждой из сторон меньше суммы длин двух
//  других сторон.
  {
    return (a < b + c) && (b < a + c) && (c < a + b);
  }

  public static boolean isRightTriangle(int a, int b, int c)
//   возвращает true, если треугольник со сторонами a, b, c является прямоугольным.
//   Из геометрии известно, что для прямоугольного треугольника выполняется теорема Пифагора
//   (сумма квадратов катетов равна квадрату гипотенузы).
  {
    return (a * a == b * b + c * c) || (a * a + b * b == c * c) || (a * a + c * c == b * b);
  }

  public static boolean isIsoscelesTriangle(int a, int b, int c)
//   возвращает true, если треугольник со сторонами a, b, c является равнобедренным.
//   Из геометрии известно, что в равнобедренном треугольнике есть две равные стороны.
  {
    return isTriangle(a, b, c) && ((a == b) || (a == c) || (b == c));
  }
}
