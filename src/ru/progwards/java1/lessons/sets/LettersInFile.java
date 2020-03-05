package ru.progwards.java1.lessons.sets;

//Реализовать класс, считывающий содержимое файла и возвращающего набор букв,
// которые встречались в этом файле. Буквы, это латинские [A..Z[ и [a..z] и русские [А..Я] и [а..я],
// остальные символы надо игнорировать
//
//        3.1 Метод public static String process(String fileName) - вернуть все буквы,
//        которые встретились в файле, сконкатенированные в виде строки.
//        Буквы должны быть упорядочены по алфавиту, типа “ADEF...”.
//        Все возникающие исключения, по работе с потоками, пробросить выше.

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class LettersInFile
{
  public static String process(String fileName) throws Exception
  {
    try (Scanner in = new Scanner(new File(fileName)))
    {
      ArrayList<Character> arr = new ArrayList<>();

      while (in.hasNext())
      {
        String s = in.next();
        for (char c : s.toCharArray())
          if (Character.isLetter(c))
            arr.add(c);
      }
//
      TreeSet<Character> tree = new TreeSet<>(arr);
      StringBuilder sb = new StringBuilder();

      for (Character ch : tree)
        sb.append(ch);

      return sb.toString();
    }

  }

  public static void main(String[] args) throws Exception
  {
    System.out.println(process("censor.txt"));
  }
}
