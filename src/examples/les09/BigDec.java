package examples.les09;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDec
{
  public static void main(String[] args)
  {
//    BigDecimal bigDec1 = BigDecimal.valueOf(10);
//    BigDecimal bigDec2 = BigDecimal.valueOf(3);
//    BigDecimal result = bigDec1.divide(bigDec2, 5, RoundingMode.HALF_UP);
//    System.out.println(result);

//    BigDecimal bigDec1 = BigDecimal.valueOf(10);
//    BigDecimal bigDec2 = BigDecimal.valueOf(3);
//    BigDecimal result = bigDec1.divide(bigDec2);

    MathContext mathContext = new MathContext(3);
    BigDecimal result = new BigDecimal("3.3333", mathContext);
    System.out.println(result);


  }
}
