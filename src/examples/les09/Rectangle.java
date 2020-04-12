package examples.les09;

import java.math.BigDecimal;

public class Rectangle
{
  Rectangle(BigDecimal a, BigDecimal b)
  {
    this.a = a;
    this.b = b;
  }

  public BigDecimal a;
  public BigDecimal b;

  boolean rectCompare(Rectangle r1, Rectangle r2)
  {
    BigDecimal x = r1.a.multiply(r1.b);
    BigDecimal y = r2.a.multiply(r2.b);
    return x.compareTo(y) == 0;
  }

  public static void main(String[] args)
  {
    Rectangle r1 = new Rectangle(new BigDecimal(73), new BigDecimal(4));
    Rectangle r3 = new Rectangle(new BigDecimal(3), new BigDecimal(4));

    System.out.println(r1.rectCompare(r1, r3));
  }
}
