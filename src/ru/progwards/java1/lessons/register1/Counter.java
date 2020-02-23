package ru.progwards.java1.lessons.register1;

//Класс Counter - счетчик, должен содержать 2 статических метода
//
//public static void inc(ByteRegister value)
//
//public static void dec(ByteRegister value)
//
//
//Класс должен корректно инкрементировать и декрементировать значение целого числа,
// работая непосредственно в ByteRegister value. При инкременте максимального числа
// (все единички) получаем 0, при декременте 0 - все единички.
public class Counter
{
  public static void inc(ByteRegister value)
  {
    value.inc();
  }

  public static void dec(ByteRegister value)
  {
    value.dec();
  }

  public static void main(String[] args)
  {
    ByteRegister b = new ByteRegister((byte) 90);
    System.out.println("Было  - " + b.toString());
    // inc(b);
    dec(b);
    System.out.println("Стало - " + b.toString());

  }
}
