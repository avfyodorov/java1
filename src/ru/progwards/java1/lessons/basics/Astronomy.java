package ru.progwards.java1.lessons.basics;

public class Astronomy
{

  public static void main(String[] args)
  {
    System.out.println("earthSquare()== " + earthSquare());
    System.out.println("mercurySquare()== " + mercurySquare());
    System.out.println("jupiterSquare()== " + jupiterSquare());
    System.out.println("earthVsMercury()== " + earthVsMercury());
    System.out.println("earthVsJupiter()== " + earthVsJupiter());
  }

  static final Double PI = 3.14;
  static final Double EARTH = 6_371.2;
  static final Double MERCURY = 2_439.7;
  static final Double JUPITER = 71_492.0;

  public static Double sphereSquare(Double r)
  //вычисляет площадь поверхности сферы радиуса R по формуле S = 4πR2.
  {
    return 4.0 * PI * Math.pow(r, 2.0);
  }

  public static Double earthSquare()
  //вычисляет площадь поверхности Земли, считая радиус равным 6 371.2 км и используют функцию sphereSquare
  {
    return sphereSquare(EARTH);
  }

  public static Double mercurySquare()
  //вычисляет площадь поверхности Меркурия, считая радиус равным 2 439,7 км  и используют функцию sphereSquare
  {
    return sphereSquare(MERCURY);
  }

  public static Double jupiterSquare()
  //вычисляет площадь поверхности Юпитера, считая радиус равным 71 492 км  и используют функцию sphereSquare
  {
    return sphereSquare(JUPITER);
  }

  public static Double earthVsMercury()
  //вычисляет отношение площади поверхности Земли к площади поверхности Меркурия используя готовые функции площадей планет
  {
    return earthSquare() / mercurySquare();
  }

  public static Double earthVsJupiter()
  //вычисляет отношение площади поверхности Земли к площади поверхности Юпитера используя готовые функции площадей планет
  {
    return earthSquare() / jupiterSquare();
  }
}
