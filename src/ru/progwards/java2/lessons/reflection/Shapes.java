package ru.progwards.java2.lessons.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

abstract class Shape
{
  abstract public String toString();
  public void draw(){
    System.out.println(this+".draw()");
  }
}

class Circle extends Shape{

  @Override
  public String toString()
  {
    return "Circle";
  }
}
class Triangle extends Shape{
  @Override
  public String toString()
  {
    return "Triangle";
  }
}
class Squart extends Shape{
  @Override
  public String toString()
  {
    return "Squart";
  }
}
public class Shapes
{
  public static void main(String[] args)
  {
    List<Shape>list= Arrays.asList(new Circle(), new Triangle(),new Squart());
    list.forEach(System.out::println);

    Class<Squart> clazz=Squart.class;
    System.out.println(clazz.getName());
    printFields(clazz);
  }

  private static void printFields(Class<Squart> clazz)
  {
    Field[] fields=clazz.getDeclaredFields();
  }
}
