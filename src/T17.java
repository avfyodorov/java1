import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class T17
{
  String createFolder(String name)
//, который создает каталог name (один уровень) в текущей папке и
// возвращает полный родителя текущего каталога
  {
    File f = new File(name);
    f.mkdir();

    Path p = Paths.get(name);
    return p.toAbsolutePath().getParent().getParent().toString();
  }

  //  Реализовать метод с сигнатурой
  boolean replaceF(String name)
  //который заменяет в файле все F на f, в случае ошибки вернуть false.
  // Для реализации пользоваться методами java.nio.file.Files.
  {
    try
    {
      Path p = Paths.get(name);
      String s = Files.readString(p);
      char[] c = s.toCharArray();
      StringBuilder b = new StringBuilder();
      for (int i = 0; i < c.length; i++)
      {
        b.append(c[i] == 'F' ? 'f' : c[i]);
      }
      Files.writeString(p, b.toString());
      return true;
    } catch (IOException e)
    {
      return false;
    }
  }

  public static void main(String[] args)
  {
    T17 t17 = new T17();
    System.out.println(t17.createFolder("qqqq"));

    t17.replaceF("qyqy9.txt");
  }
}
