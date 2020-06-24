package ru.progwards.java2.lessons.examples;

//Напишите метод с сигнатурой String reverseChars(String str),
// который возвращает символы строки str в обратном порядке.
// Т.е. если на входе "12345" на выходе должно быть "54321"

public class Lesson_02_03
{
  static String reverseChars(String str)
  {
    int len = str.length();
    if (len <= 1) return str;
    String head = str.substring(0, len / 2);
    String tail = str.substring(len / 2, len);
    return reverseChars(tail) + reverseChars(head);
  }

  public static String reverseStringWithRecursion(String inputString)
  {
    String rightPart;
    String leftPart;

    int length = inputString.length();

    // заканчиваем рекурсивный обход
    if (length <= 1)
    {
      return inputString;
    }

    leftPart = inputString.substring(0, length / 2);

    rightPart = inputString.substring(length / 2, length);

    // рекурсивно переворачиваем левую и правую часть входной строки
    return reverseStringWithRecursion(rightPart) + reverseStringWithRecursion(leftPart);
  }

  public static void main(String[] args)
  {
    System.out.println(reverseChars("12345"));
  }
}
