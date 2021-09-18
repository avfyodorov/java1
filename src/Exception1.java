import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Exception1
{
  public static void f(){
    try {
      FileReader f=new FileReader("log.txt");

      try {

      }
      finally {

      }
    }
    catch (IOException e) {

    }
  }
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
