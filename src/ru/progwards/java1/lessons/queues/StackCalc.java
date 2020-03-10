package ru.progwards.java1.lessons.queues;

//Стековый калькулятор (есть даже такой стековый язык программирования - Forth).
// Стек, это структура данных, работающая по принципу LIFO - last in first out
// (последний вошедший выходит первый), это противоположность привычной очереди
// FIFO - first in first out - первый вошедший выходит первый
//
//        Реализовать class StackCalc, который содержит стек чисел с плавающей точкой (double).
//        Выбрать наиболее удобную для этого коллекцию. Реализовать методы работы со стеком:
//
//        3.1 public void push(double value) - положить value на вершину стека
//
//        3.2 public double pop() - взять (убрать) значение с вершины стека
//
//        3.3 public void add() - сложить 2 верхних значения на стеке, результат положить на стек.
//        В итогу в стеке должно быть на один элемент меньше
//
//        3.4 public void sub() - вычесть верхнее значение на стеке, из следующего по глубине,
//        результат положить на стек. В итоге в стеке должно быть на один элемент меньше
//
//        3.5 public void mul() - умножить 2 верхних значения на стеке, результат положить на стек.
//        В итогу в стеке должно быть на один элемент меньше
//
//        3.6 public void div() - поделить на верхнее значение на стеке, следующее по глубине,
//        результат положить на стек. В итоге в стеке должно быть на один элемент меньше
//
//        Class Calculate, который содержит методы:
//
//        3.7 public static double calculation1(), возвращающую результат вычисления следующей формулы:
//
//        2.2*(3+12.1), используя класс StackCalc
//
//        3.8 public static double calculation2(), возвращающую результат вычисления следующей формулы:
//
//        (737.22+24)/(55.6-12.1)+(19-3.33)*(87+2*(13.001-9.2)), используя класс StackCalc
//


import java.util.ArrayDeque;
import java.util.Deque;

public class StackCalc
{
  Deque<Double> stack = new ArrayDeque<>();

  public static void main(String[] args)
  {
    System.out.println("calc1 = " + Calculate.calculation1());
    System.out.println("calc2 = " + Calculate.calculation2());
  }

  public void push(double value)// - положить value на вершину стека
  {
    stack.push(value);
  }

  public double pop() //- взять (убрать) значение с вершины стека
  {
    return stack.pop();
  }

  public void add() //- сложить 2 верхних значения на стеке, результат положить на стек.
//        В итогу в стеке должно быть на один элемент меньше
  {
    push(pop() + pop());
  }

  public void sub() //- вычесть верхнее значение на стеке, из следующего по глубине,
//        результат положить на стек. В итоге в стеке должно быть на один элемент меньше
  {
    push(-pop() + pop());
  }

  public void mul() //- умножить 2 верхних значения на стеке, результат положить на стек.
//        В итогу в стеке должно быть на один элемент меньше
  {
    push(pop() * pop());
  }

  public void div() //- поделить на верхнее значение на стеке, следующее по глубине,
//        результат положить на стек. В итоге в стеке должно быть на один элемент меньше
  {
    double d = pop();
    push(pop() / d);
  }
}


class Calculate
{
  public static double calculation1()
//    33.22 ==    2.2*(3+12.1), используя класс StackCalc
  {
    StackCalc sc = new StackCalc();

    sc.push(2.2);
    sc.push(3);
    sc.push(12.1);

    sc.add();
    sc.mul();

    return sc.pop();
  }

  public static double calculation2()
//        (737.22+24)/(55.6-12.1)+(19-3.33)*(87+2*(13.001-9.2)), используя класс StackCalc
  {
    StackCalc sc = new StackCalc();

    sc.push(737.22);
    sc.push(24.0);
    sc.add();

    sc.push(55.6);
    sc.push(12.1);
    sc.sub();

    sc.div();

    sc.push(13.001);
    sc.push(9.2);
    sc.sub();

    sc.push(2.0);
    sc.mul();

    sc.push(87.0);
    sc.add();

    sc.push(19.0);
    sc.push(3.33);
    sc.sub();

    sc.mul();

    sc.add();

    return sc.pop();
  }
}