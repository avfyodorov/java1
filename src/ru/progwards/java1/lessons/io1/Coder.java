package ru.progwards.java1.lessons.io1;

//public static void codeFile(String inFileName, String outFileName,
// char[] code, String logName), в котором прочитать файл inFileName и
// перекодировать его посимвольно в соответствии с заданным шифром,
// результат записать в outFileName. Шифр задается маcсивом char[] code,
// где каждому символу symbol оригинального файла соответствует символ
// code[(int)symbol] выходного файла. В случае ошибок, в файл с именем logName
// вывести название ошибки через метод класса Exception - getMessage()

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Coder
{
  public static void toLog(String logName, String msg)
  {
    System.out.println(msg);
    try
    {
      FileWriter log = new FileWriter(logName, true);

      try
      {
        log.write(msg + "\n");
      } finally
      {
        log.close();
      }
    } catch (IOException e)
    {
      System.out.println(e.getMessage());
    }
  }

  public static void codeFile(String inFileName, String outFileName,
                              char[] code, String logName)
  {
    try
    {
      FileReader reader = new FileReader(inFileName);
      Scanner scanner = new Scanner(reader);

      FileWriter writer = new FileWriter(outFileName);

      StringBuilder str_out = new StringBuilder();

      try
      {
        while (scanner.hasNextLine())
        {
          String str_in = scanner.nextLine();
          str_out.delete(0, str_out.length());

          for (int i = 0; i < str_in.length(); i++)
            if ((int) (str_in.charAt(i)) < code.length)
              str_out.append(code[(int) str_in.charAt(i)]);
            else
              str_out.append('*');

          str_out.append("\n");
          writer.write(str_out.toString());

        }
      } finally
      {
        writer.close();
        reader.close();
      }
    } catch (IOException e)
    {
      toLog(logName, e.getMessage());
    }
  }

  public static void main(String[] args)
  {
//=====
    char[] code = new char[256];
    Arrays.fill(code, '*');
    for (int i = 48; i < 58; i++)
      code[i] = (char) (i + 16);
//-----

    codeFile("qyqy7.txt", "dydy.txt", code, "log.txt");
  }
}
