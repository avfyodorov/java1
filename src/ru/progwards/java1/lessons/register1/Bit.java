package ru.progwards.java1.lessons.register1;

//Реализовать класс, содержащий один бит.
// Внутри данные хранить в переменной типа boolean, методы:
public class Bit
{
  private boolean value;

  public Bit()
  {
    value = false;
  }

  public Bit(boolean value)
  {
    this.value = value;
  }

  @Override
  public String toString()// - должен выводить 1 или 0
  {
    return value ? "1" : "0";
  }
}
