package ru.progwards.java1.lessons.register2;


//Класс Counter - счетчик, должен содержать 2 статических метода
//Класс должен корректно инкрементировать и декрементировать значение целого числа,
// работая непосредственно в ByteRegister value. При инкременте максимального числа
// (все единички) получаем 0, при декременте 0 - все единички.
//--------------------------------------------------------------------

//========================================================================================
//Сделать рефакторинг(изменить сигнатуру методов) класса Counter - счетчик, на спецификацию:
//
//public static void inc(Register value)
//
//public static void dec(Register value)
//Класс должен корректно инкрементировать и декрементировать значение целых чисел

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
