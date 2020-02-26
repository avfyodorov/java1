package ru.progwards.java1.lessons.register2;

//Класс ShiftRegister - регистр со сдвигом, должен содержать 2 статических метода
//
//Класс должен корректно реализовывать сдвиг влево и сдвиг вправо значения целого числа,
// работая непосредственно в  ByteRegister value
//------------------------------------------------------

//======================================================
//Сделать рефакторинг класса ShiftRegister - регистра со сдвигом, на спецификацию
//
//public static void left(Register value)
//
//public static void right(Register value)
//Класс должен корректно реализовывать сдвиг влево и сдвиг вправо значения целого числа
public class ShiftRegister
{
  public static void left(ByteRegister value)
  {
    value.left();
  }

  public static void right(ByteRegister value)
  {
    value.right();
  }

  public static void main(String[] args)
  {
    ByteRegister b = new ByteRegister((byte) 90);
    System.out.println("Было  - " + b.toString());

    left(b);

    System.out.println("Стало - " + b.toString());

  }
}
