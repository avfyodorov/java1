package ru.progwards.java1.lessons.bigints;

//Реализовать класс ArrayInteger - целого числа произвольной длины на массиве byte[] digits;
// Каждый элемент массива digits[i] может хранить только цифру, то есть число от 0 до 9.
// Например, число 159 должно занять 3 ячейки массива digits[0] = 9; digits[1] = 5;
// digits[2] = 1;
//
//        Реализовать методы:
//
//        3.1 ArrayInteger(int n) - инициализирует класс, с максимальной точностью n цифр
//        (размер массива)
//
//        3.2 void fromInt(BigInteger value) - установить свое значение, взяв его из value
//        (уложить BigInteger во внутренний массив)
//
//        3.3 BigInteger toInt() - привести свое значение к BigInteger
//        (преобразовать из массива в BigInteger)
//
//        3.4 boolean add(ArrayInteger num) - сложить 2 числа, не используя BigInteger,
//        а  используя массив digits, результат поместить в экземпляр ArrayInteger,
//        у которого был вызван метод. При переполнении вернуть false, при этом само число
//        сбросить в 0


import java.math.BigInteger;
import java.util.Arrays;

public class ArrayInteger
{
  private byte[] digits;
  private boolean sign;

  public byte getDigit(int n)
  {
    return n >= 0 && n < digits.length ? digits[n] : 0;
  }

  public int getArrayLength()
  {
    return digits.length;
  }

  public boolean isSign()
  {
    return sign;
  }

  public ArrayInteger(int n)
  {
    digits = new byte[n];
    sign = true;
  }

  void debugPrint()
  {
    System.out.println("Length = " + digits.length + ",    Sign = " + sign);
    System.out.println(Arrays.toString(digits));
  }

  public void fromInt(BigInteger value)
  {
    int i = 0;
    BigInteger[] arr = {value.abs(), BigInteger.ZERO};
    sign = value.compareTo(BigInteger.ZERO) >= 0;

    while ((i < digits.length) && (arr[0].compareTo(BigInteger.TEN) >= 0))
    {
      arr = arr[0].divideAndRemainder(BigInteger.TEN);
      digits[i] = arr[1].byteValue();
      i++;
    }

    if (i < digits.length)
      digits[i] = arr[0].byteValue();

  }

  public BigInteger toInt()
  {
    BigInteger res = BigInteger.valueOf(digits[0]);
    BigInteger p = BigInteger.ONE;

    for (int i = 1; i < digits.length; i++)
    {
      p = p.multiply(BigInteger.TEN);
      res = res.add(p.multiply(BigInteger.valueOf(digits[i])));
    }

    if (sign == false)
      res = res.multiply(BigInteger.valueOf(-1));

    return res;
  }

  public boolean add(ArrayInteger num)
  {
    if (digits.length < num.getArrayLength())
    {
      Arrays.fill(digits, (byte) 0);
      sign = true;
      return false;
    }

    byte o = 0;
    for (int i = 0; i < digits.length; i++) {
      digits[i] = (byte) (digits[i] + num.getDigit(i) + o);
      if (digits[i] > 9)      {
        digits[i] = (byte) (digits[i] - 10);
        o = 1;
      } else
        o = 0;
    }

    if (o == 0)
      return true;

    Arrays.fill(digits, (byte) 0);
    sign = true;
    return false;
  }

  public static void main(String[] args)
  {
    ArrayInteger a = new ArrayInteger(15);
    a.fromInt(new BigInteger("100000000000"));
    a.debugPrint();

    System.out.println(a.toInt().toString());

    ArrayInteger b = new ArrayInteger(7);
    b.fromInt(new BigInteger("6789610"));
    b.debugPrint();

    ArrayInteger c = new ArrayInteger(24);
    c.fromInt(new BigInteger("922337203685477580746043"));
    c.debugPrint();

    System.out.println(b.add(c));
    b.debugPrint();
//    System.out.println(a.toInt().toString());

  }

}
