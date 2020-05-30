package ru.progwards.java2.lessons.calculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator
{
  public static int calculate(String expression)
//  , который вычисляет арифметическое выражение, заданное в виде символьной строки.
//  Выражение содержит только целые цифры и знаки арифметических операций +-*/
//  При вычислении должны учитываться приоритеты операций, например,
//  результат вычисления выражения "2+3*2" должен быть равен 8.
//  По оригинальному условию задачи все числа содержат не более одной цифры, пробелов в строке нет.

//  Задача 2. Дополнительные условия
//  Добавить возможность использования скобок () для задания приоритетов операций.
//  На сколько сильно нужно будет изменить код, что бы это заработало?
  {
    String stack = toPolish(expression);
    return calc(stack);
  }

  static String ops = "()-+*/";
  static int[] weight = {0, 1, 2, 2, 3, 3};

  public static String toPolish(String expression)
  {
    Deque<Character> stack = new ArrayDeque<>();

    StringBuilder res = new StringBuilder();
    for (char c : expression.toCharArray())
    {
//числа сразу переводятся в рез-т
      if (Character.isDigit(c))
        res.append(c);
      else
      {
        int i = ops.indexOf(c);
        if (i < 0) return null;
// если ( или пустой или вес входящего больше -- просто добавить
        if (weight[i] == 0 || stack.isEmpty() || weight[i] > weight[ops.indexOf(stack.peek())])
          stack.push(c);
        else
        {
          if (c == ')')
          {
// если ) -- вытащить всё, до (.  () - удаляются
            while (!stack.isEmpty() && stack.peek() != '(')
              res.append(stack.pop());
            stack.pop();
          } else
          {
//вес входящего не больше - вытащить из стека и положить
            while (!stack.isEmpty() && weight[i] <= weight[ops.indexOf(stack.peek())])
              res.append(stack.pop());
            stack.push(c);
          }
        }
      }
    }
    while (!stack.isEmpty())
      res.append(stack.pop());

    return res.toString();
  }

  public static int calc(String expression)
  {
    Deque<Integer> stack = new ArrayDeque<>();
    for (char c : expression.toCharArray())
    {
      if (Character.isDigit(c))
        stack.push(Integer.parseInt(Character.toString(c)));
      else
      {
        if (c=='*')
          stack.push(stack.pop()*stack.pop());
        else if (c=='+')
          stack.push(stack.pop()+stack.pop());
        else if (c=='/')
        {
          Integer b=stack.pop();
          stack.push(stack.pop() / b);
        }
        else if (c=='-')
        {
          Integer b=stack.pop();
          stack.push(stack.pop() - b);
        }
      }
    }
      return stack.pop();
  }

  public static void main(String[] args)
  {
    System.out.println(calculate("2+3*2"));
    System.out.println(calculate("2+3*2-8/(1+3)"));
  }
}
