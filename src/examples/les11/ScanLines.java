package examples.les11;

import java.util.Scanner;

public class ScanLines
{
  public void scanLines()
  {
    try (Scanner sc = new Scanner(System.in))
    {
      String str = "";
      while (sc.hasNextLine())
      {
        str = sc.nextLine();

        if (str.contains("/stop"))
          break;
        else if (str.contains("Привет"))
          System.out.println("Здравствуйте!");
        else if (str.contains("как дела"))
          System.out.println("Хорошо");
        else
          System.out.println(str);

      }
    }
  }

  public static void main(String[] args)
  {
    ScanLines sc = new ScanLines();
    sc.scanLines();
  }
}
