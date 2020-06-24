package ru.progwards.java2.lessons.gc;

public class OutOfMemoryException extends Exception
{
  public OutOfMemoryException()
  {
    super("Слюшай, памяти мало! Нэт совсем, да?");
  }
}
