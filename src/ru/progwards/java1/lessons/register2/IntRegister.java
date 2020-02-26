package ru.progwards.java1.lessons.register2;

//Реализовать класс, ByteRegister, содержащий 8 бит,
// реализованных на классе Bit, как целое беззнаковое число, хранить биты в массиве.
//
//public ByteRegister() - инициализация нулями
//
//public ByteRegister(byte value) - обратить внимание на то, что в Java byte знаковый,
// а у нас нет. При сохранении просто копируем побитно, просто по другому интерпретируем
// значение.
//
//public String toString() - вывод в двоичном виде
//
//public String toDecString() - вывод в десятичной системе счисления,
// при преобразовании рассматриваем значение как целое положительное число


//==========================================================================================
//Реализовать класс, IntRegister, содержащий 32 бит, реализованных на классе Bit, методы:
//
//public IntRegister() - инициализация нулями
//
//public Int(int value)
//
//public String toString() - вывод в двоичном представлении
//
//public String toDecString() - вывод в десятичном представлении,
// при преобразовании рассматриваем значение как целое знаковое число.
// Отрицательные числа хранятся в дополнительном коде.Старший бит дополнительного кода
// отвечает за знак числа. Более подробно можно почитать в Википедии

public class IntRegister
{
  private Bit[] bits = new Bit[8];

  public IntRegister()
  {
    setValue((byte) 0);
  }

  public IntRegister(byte value)
  {
    setValue(value);
  }

//=====
  public void setValue(byte value)
  {
    for (int i = 0; i < bits.length; i++)
      bits[i] = new Bit(((value >> i) & 1) == 1);
  }

  public int getValue()
  {
    int m = 1;
    int res = 0;

    for (int i = 0; i < bits.length; i++)
      res = res + (m << i) * bits[i].get();

    return res;
  }
//-----

  @Override
  public String toString()
  {
    StringBuilder res = new StringBuilder("");

    for (int i = bits.length - 1; i >= 0; i--)
      res.append(bits[i].toString());

    return res.substring(0);
  }

  public String toDecString()
  {
    return String.valueOf(getValue());
  }

//=====
  public void inc()
  {
    setValue((byte) (getValue() + 1));
  }

  public void dec()
  {
    setValue((byte) (getValue() - 1));
  }

  public void left() { setValue((byte) (getValue() << 1)); }

  public void right() { setValue((byte) (getValue() >> 1)); }
//-----

  public static void main(String[] args)
  {
    IntRegister b = new IntRegister((byte) 65);
    System.out.println(b);
    System.out.println(b.toDecString());

    b.right();
    System.out.println(b);
    System.out.println(b.toDecString());

//    b.dec();
//    System.out.println(b);
//    System.out.println(b.toDecString());

  }
}
