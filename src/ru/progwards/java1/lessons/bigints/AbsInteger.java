package ru.progwards.java1.lessons.bigints;

// Реализовать потомков ByteInteger, ShortInteger, IntInteger,
// хранящих значение целого числа соответствующего типа.
// У каждого типа реализовать конструктор, принимающей значение числа
// соответствующего типа, для ByteInteger - byte, ShortInteger - short и
// IntInteger - int. Реализовать унаследованные публичные методы String toString(),
// посредством которых возвращать приведенное к строке значение соответствующего
// классу типа.
//
//         2.3 У класса AbsInteger реализовать метод static AbsInteger add(AbsInteger num1,
//         AbsInteger num2) - который вычисляет сумму num1 и num2. При этом надо учесть,
//         что num1 и num2 могут быть разных типов. Для того, чтобы это реализовать продумать,
//         какие дополнительные методы нужно сделать у AbsInteger и его потомков.
//
//        Привести результат к наиболее подходящему типу, т.е. -128..127 это ByteInteger и
//        т.д.
//

public abstract class AbsInteger
{
  public abstract int getValue();

  static AbsInteger add(AbsInteger num1, AbsInteger num2)
  {
    int res = num1.getValue() + num2.getValue();

    if (res >= Byte.MIN_VALUE && res <= Byte.MAX_VALUE)
      return new ByteInteger((byte) res);
    else if (res >= Short.MIN_VALUE && res <= Short.MAX_VALUE)
      return new ShortInteger((short) res);
   else
     return new IntInteger(res);

  }

  public static void main(String[] args)
  {
    ByteInteger b = new ByteInteger((byte) 123);
    System.out.println(b.toString());
    System.out.println(add(new IntInteger(12345),new ByteInteger((byte) 8)));
  }
}

class ByteInteger extends AbsInteger
{
  private byte val;
  public  int getValue(){return val;}

  public ByteInteger(byte val)
  {
    this.val = val;
  }

  @Override
  public String toString()
  {
    return String.valueOf(val);
  }
}

class ShortInteger extends AbsInteger
{
  private short val;
  public  int getValue(){return val;}

  public ShortInteger(short val)
  {
    this.val = val;
  }

  @Override
  public String toString()
  {
    return String.valueOf(val);
  }
}

class IntInteger extends AbsInteger
{
  private int val;
  public  int getValue(){return val;}

  public IntInteger(int val)
  {
    this.val = val;
  }

  @Override
  public String toString()
  {
    return String.valueOf(val);
  }
}


