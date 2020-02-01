
import java.math.BigInteger;
import java.util.Arrays;

class Rock0
{
  Rock0()
  {
    System.out.print("Rock, ");
  }
}

class Str11
{
  String s;

  Str11()
  {
    System.out.println(s);
  }
}

class Rock1
{
  Rock1(int i)
  {
    System.out.print("Rock, " + i + "  ");
  }
}

public class Rock
{
  public static void main(String[] args)
  {
    int[] a1 = {0, 2, 2};
    int[] a2 = {1, 3};
    System.out.println(Arrays.toString(mergeArrays(a1, a2)));

    //=====
//   System.out.println(factorial(5).longValue());
  }

  public static int[] mergeArrays(int[] a1, int[] a2)
  {
    int[] res = new int[a1.length + a2.length];

    int i1=0, i2=0;
    for (int i=0; i<res.length; i++) {

      if (i1 > a1.length-1) {
        res[i] = a2[i2];
        i2++;
      }
      else if (i2 > a2.length-1) {
        res[i] = a1[i1];
        i1++;
      }
      else if (a1[i1] < a2[i2]) {
        res[i] = a1[i1];
        i1++;
      }
      else {
        res[i] = a2[i2];
        i2++;
      }
    }

    return res; // your implementation here
  }


  public static BigInteger factorial(int value)
  {
    BigInteger res = BigInteger.valueOf(1);//число счетчик бигинтеджер которым будем считать
    for (int i = 1; i <= value; i++) {//будем умножать на каждое число пока число меньше внесенного
      res = res.multiply(BigInteger.valueOf(i));//
    }
    return res;//
  }

/*
  String s="Madam, I'm Adam!".replaceAll("[^a-zA-Z0-9]","");
  StringBuilder res=new StringBuilder(s);
  res=res.reverse();
    s.equalsIgnoreCase(res.toString());
    System.out.println();
*/

  public static boolean isPowerOfTwo(int value)
  {
    int i = Math.abs(value);
    return (value & (value - 1)) == 0;
  }

  public static char charExpression(int a)
  {
    return (char) ('\\' + a);
  }

}
