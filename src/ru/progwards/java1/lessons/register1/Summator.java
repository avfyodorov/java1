package ru.progwards.java1.lessons.register1;

//Класс Summator - сумматор, должен содержать 1 статический метод
//
// Класс должен корректно реализовывать сложение целых положительных чисел,
// результат помещается в первый регистр value1.
// Метод возвращает true если не было переполнения (выход за границы значения числа) и
// false если было
public class Summator
{
  public static boolean add(ByteRegister value1, ByteRegister value2)
  {
    int sum = value1.getValue() + value2.getValue();
    value1.setValue((byte) sum);
    return sum < 256;
  }

  public static void main(String[] args)
  {
    ByteRegister a = new ByteRegister((byte) 65);
    ByteRegister b = new ByteRegister((byte) 205);

    System.out.println(a);
    System.out.println(a.toDecString());

    System.out.println(add(a, b));

    System.out.println(a);
    System.out.println(a.toDecString());

  }
}
