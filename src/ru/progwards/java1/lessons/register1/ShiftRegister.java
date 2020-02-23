package ru.progwards.java1.lessons.register1;

//Класс ShiftRegister - регистр со сдвигом, должен содержать 2 статических метода
//
//Класс должен корректно реализовывать сдвиг влево и сдвиг вправо значения целого числа,
// работая непосредственно в  ByteRegister value
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
