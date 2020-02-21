package ru.progwards.java1.lessons.io1;

// прочитать файл inFileName и удалить символы, содержащиеся в String filter,
// результат записать в выходной файл. В случае возникновения ошибки, пробросить
// стандартное исключение выше, корректно закрыв все ресурсы
//
//        Например файл содержит:
//        Java — строго типизированный объектно-ориентированный язык программирования, разработанный компанией Sun Microsystems (в последующем приобретённой компанией Oracle).
//        obscene = " -,.()"
//        Должен выдать результат:
//        JavaстроготипизированныйобъектноориентированныйязыкпрограммированияразработанныйкомпаниейSunMicrosystemsвпоследующемприобретённойкомпаниейOracle

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CharFilter
{
  public static void filterFile(String inFileName, String outFileName, String filter) throws IOException
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
          if (filter.indexOf(str_in.charAt(i)) == -1)
            str_out.append(str_in.charAt(i));

        str_out.append("\n");
        writer.write(str_out.toString());
      }

    } finally
    {
      writer.close();
      reader.close();
    }

  }

  public static void main(String[] args)
  {
    try
    {
      filterFile("qyqy.txt", "mymy.txt", " -,.()");
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
