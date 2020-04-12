package examples.les11;

//Реализовать метод с сигнатурой public String setStars(String filename)
// который читает файл filename и меняет в нем каждый 10-йбайт на символ *,
// при этом конкатенируя оригинальный символ в строку ответа.
//        В случае ошибки выбросить исключение IOException со строкой сообщения:
//        равной имени класса оригинального сообщения
//
//        Например,при содержимом файла:
//
//        0123456789012345678A012345678B01
//
//        новое содержимое должно быть
//
//        012345678*012345678*012345678*01
//
//        и метод должен вернуть "9AB"

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SetStars
{
  public String setStars(String filename) throws IOException
  {
    String res = "";

    try (RandomAccessFile f = new RandomAccessFile(filename, "rw"))
    {
      long pos = 9;
      while (pos < f.length())
      {
        f.seek(pos);
        int i = f.read();
        res = res + String.valueOf((char) i);
        f.seek(pos);
        f.write('*');

        pos = pos + 10;
      }
    } catch (IOException e)
    {
      throw new IOException(e.getClass().getName());
    }
    return res;
  }

  public static void main(String[] args)
  {
    SetStars ss = new SetStars();
    try
    {
      System.out.println(ss.setStars("setstars.txt"));
    } catch (IOException e)
    {
      System.out.println(e.getMessage());
    }
  }
}
