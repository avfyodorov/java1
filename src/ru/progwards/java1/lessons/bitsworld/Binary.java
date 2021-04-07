package ru.progwards.java1.lessons.bitsworld;

// Реализовать конструктор
//public Binary(byte num).
//        3.2 Реализовать метод
//public String toString(), который возвращает двоичное представление числа типа byte,
// используя только битовые операции. В выводимом значении всегда должно быть 8 символов

public class Binary
{
  private byte num;

  public Binary(byte num)
  {
    this.num = num;
  }

  @Override
  public String toString()
  {
    String res = "";

    for (int i = 0; i < 8; i++)
    {
//логическое  & c 1
      res = (((num & 1) == 0) ? "0" : "1") + res;
//Сдвинуть число на 1 бит
      num = (byte) (num >> 1);
    }
//    res = ((((1 << i) & num) == 0) ? "0" : "1") + res;

    return res;
  }

  public static void main(String[] args)
  {
    System.out.println(new Binary((byte) -81).toString());
    System.out.println(new Binary((byte) 1).toString());
    System.out.println(new Binary((byte) 127).toString());
    System.out.println(new Binary((byte) -40).toString());
    System.out.println(new Binary((byte) -1).toString());
  }
}
//        Например:
//        0: "00000000"
//        1: "00000001"
//        127: "01111111"
//        -128: "10000000"
//        -1: "11111111"

