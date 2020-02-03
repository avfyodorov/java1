package ru.progwards.java1.lessons.basics;

public class ReverseDigits
{
  public static void main(String[] args)
  {
    System.out.println(reverseDigits(123));
  }

  public static int reverseDigits(int number)
  {
    int n1 = number / 100;
    int n2 = (number - n1 * 100) / 10;
    int n3 = number - n1 * 100 - n2 * 10;
    return n3 * 100 + n2 * 10 + n1;
  }
}
