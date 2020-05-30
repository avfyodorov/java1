package ru.progwards.java2.lessons.lesson1;

public class HelloWorldWithParams
{
  public static void main(String[] args)
  {
    if (args.length==0)
      System.out.println("Without params");
    else
      for (int i = 0; i < args.length; i++)
        System.out.println("Param "+i+" : "+args[i]);

  }
}
