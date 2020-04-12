package examples;

import java.util.Objects;

public class Rectangle
{
  private double a;
  private double b;

  public Rectangle(double a, double b)
  {
    this.a = a;
    this.b = b;
  }

  public double area()
  {

    return a * b;
  }

  @Override
  public boolean equals(Object anObject)
  {
    if (this == anObject) return true;
    if (anObject == null || getClass() != anObject.getClass()) return false;
    Rectangle rectangle = (Rectangle) anObject;
    return Double.compare(rectangle.area(), area())==0;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(a, b);
  }

  public int compareTo(Rectangle anRectangle)
  {
    if (area() > anRectangle.area())
      return 1;
    else if (area() < anRectangle.area())
      return -1;

    return 0;
  }

  public static void main(String[] args)
  {
//    прямоугольник 2x2  >  прямоугольника 1x1 - результат 1
//    прямоугольник 2x3 == прямоугольнику 3x2 - результат 0
//    прямоугольник 2x2  <  прямоугольника 3x3 - результат -1

    System.out.println(new Rectangle(2, 2).compareTo(new Rectangle(1, 1)));
    System.out.println(new Rectangle(2, 3).compareTo(new Rectangle(3, 2)));
    System.out.println(new Rectangle(2, 2).compareTo(new Rectangle(3, 3)));

  }
}
