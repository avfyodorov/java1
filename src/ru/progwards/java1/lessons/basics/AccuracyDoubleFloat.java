package ru.progwards.java1.lessons.basics;

public class AccuracyDoubleFloat
{
  static final double PI = 3.14;
  static final float PI_F = 3.14f;
  static final double EARTH = 6_371.2;
  static final float EARTH_F = 6_371.2f;

  public static void main(String[] args)
  {
    System.out.println("" + volumeBallDouble(EARTH));
    System.out.println("" + volumeBallFloat(EARTH_F));
    System.out.println("" + calculateAccuracy(EARTH));
  }

  public static double volumeBallDouble(double radius)
  // будет возвращать объём шара с радиусом radius и основана на типе double.
  {
    return 4.0 * PI * Math.pow(radius, 3.0) / 3.0;
  }

  public static float volumeBallFloat(float radius)
  //будет возвращать объём шара с радиусом radius и основана на типе float.
  {
    return (float) (4.0 / 3.0 * PI_F * Math.pow(radius, 3.0));
  }

  public static double calculateAccuracy(double radius)
  //будет возвращать разницу между функциями volumeBallDouble и volumeBallFloat
  //(они должны быть вызваны из неё с параметром radius).
  {
    return volumeBallDouble(radius) - volumeBallFloat((float) radius);
  }
}
