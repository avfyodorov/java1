package ru.progwards.java1.lessons.bitsworld;

//Реализовать функцию public static int checkBit(byte value, int bitNumber)
// которая возвращает значение бита с порядковым номером bitNumber от параметра value.
// При этом следует помнить, что нумерация начинается с нуля и нулевой бит является младшим.
//
//        Подсказки:
//
//        при реализации этой функции следует использывать опыт, полученный при решении
//        предыдущей задачи;
//        кроме того, удобно использовать битовую операцию сдвиг вправо >>.


public class CheckBit
{
  public static int checkBit(byte value, int bitNumber)
  {
    if (bitNumber < 0 || bitNumber > 7)
      return 0;

    return ((1 << bitNumber) & value) == 0 ? 0 : 1;
  }

  public static void main(String[] args)
  {
    System.out.println(checkBit((byte) 0b11011000, 3));
  }
}
