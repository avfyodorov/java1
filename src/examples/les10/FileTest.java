package examples.les10;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileTest
{
  public static void main(String[] args) throws IOException
  {
    FileWriter f = new FileWriter("qyqy.txt");
    f.write("1\n");
    f.write("2\n");
    f.write("3\n");
    f.close();

    FileTest ft = new FileTest();
    System.out.println(ft.lineCount("qyqy8.txt"));
  }

  private int lineCount(String filename) throws IOException
  {
    int res = 0;
    try
    {
      FileReader reader = new FileReader(filename);
      Scanner scanner = new Scanner(reader);
      try
      {
        while (scanner.hasNextLine())
        {
          scanner.nextLine();
          res++;
        }
      } finally
      {
        reader.close();
      }
    } catch (IOException e)
    {
      throw new IOException("файл не найден");
    }
    return res;
  }
}