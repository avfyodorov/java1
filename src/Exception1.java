import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exception1
{
  public static void main(String[] args)
  {
    File f=new File("qyqy");
    try {
      Scanner scn=new Scanner(f);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
