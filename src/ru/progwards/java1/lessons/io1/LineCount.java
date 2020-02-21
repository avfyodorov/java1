package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//Создать статический метод public static int calcEmpty(String fileName),
// в котором посчитать количество пустых строк в файле.
// В случае возникновения ошибок, вернуть -1
public class LineCount
{
  public static int calcEmpty(String fileName)
  {
    int res = 0;
    try
    {
      FileReader reader = new FileReader(fileName);
      Scanner scanner = new Scanner(reader);
      try
      {
        while (scanner.hasNextLine())
          if(scanner.nextLine().isEmpty())
            res++;

      } finally
      {
        reader.close();
      }
    } catch (IOException e)
    {
      return -1;
    }
    return res;
  }

  public static void main(String[] args)
  {
    System.out.println("Найдено пустых стpок: "+calcEmpty("qyqy.txt"));
  }
}
