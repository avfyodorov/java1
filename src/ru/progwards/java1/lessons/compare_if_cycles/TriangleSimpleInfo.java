package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleSimpleInfo {
   public static void main(String[] args) {
      System.out.println(isEquilateralTriangle(5, 5, 5));
   }

   //   получает параметрами длины сторон треугольника, а вернуть должна наибольшую длину стороны.
   public static int maxSide(int a, int b, int c) {
      if (a >= b) {
         if (a > c) return a;
         else return c;
      } else {
         if (b > c) return b;
         else return c;
      }
   }

   public static int minSide(int a, int b, int c)
//  получает параметрами длины сторон треугольника, а вернуть должна наименьшую длину стороны.
   {
      if (a <= b) {
         if (a < c) return a;
         else return c;
      } else {
         if (b < c) return b;
         else return c;
      }
   }

   public static boolean isEquilateralTriangle(int a, int b, int c)
//  получает параметрами длины сторон треугольника, а вернуть должна true,
//  если треугольник равносторонний и false в противном случае.
   {
      return (a == b) && (a == c);
   }
}
