import java.io.File;
import java.io.IOException;

public class C17_File
{
  static String pathname = "C://Lib//Java";

  public static void main(String[] args) throws IOException
  {
    pathToFile("a//b//..//file.txt");
    pathToFile(".//a//b//..//b//c//file.txt");
    pathToFile("a//..//b//c//file.txt");
    pathToFile("a//.//b//..//c//.//file.txt");
    pathToFile("a//b//c//file.txt");



//    createDirs();

//    workWithFile("//MyFile.txt");
    //listFiles(pathname);
    //workdir(pathname, "//NewDir");
  }
  /*
  a\b\..\file.txt
        .\a\b\..\b\c\.\file.txt
  a\..\b\c\file.txt
  a\.\b\..\c\.\file.txt
  a\b\c\file.txt
*/
  static void pathToFile(String s) throws IOException
  {
    File f = new File(s);
    System.out.println(f.getCanonicalPath());
  }

  private static void createDirs()
  {
    File dir = new File(pathname + "//a//b//c");
    boolean created = dir.mkdirs();
    if (created)
      System.out.println("цепочка каталогов создана");
  }

  public static void workWithFile(String filename) {

      // определяем объект для каталога
      File myFile = new File(pathname+filename);
      System.out.println("File name: " + myFile.getName());
      System.out.println("Parent folder: " + myFile.getParent());
      if(myFile.exists())
        System.out.println("File exists");
      else
        System.out.println("File not found");

      System.out.println("File size: " + myFile.length());
      if(myFile.canRead())
        System.out.println("File can be read");
      else
        System.out.println("File can not be read");

      if(myFile.canWrite())
        System.out.println("File can be written");
      else
        System.out.println("File can not be written");

      // создадим новый файл
      File newFile = new File(pathname+"//MyFile");
      try
      {
        boolean created = newFile.createNewFile();
        if(created)
          System.out.println("File has been created");
      }
      catch(IOException ex){

        System.out.println(ex.getMessage());
      }
    }


  static void workdir(String pathname, String dirname)
  {
    // определяем объект для каталога
    File dir = new File(pathname + dirname);
    boolean created = dir.mkdir();
    if (created)
      System.out.println("Folder has been created");
    // переименуем каталог
    File newDir = new File(pathname + "//NewDirRenamed");
    dir.renameTo(newDir);
    // удалим каталог
    boolean deleted = newDir.delete();
    if (deleted)
      System.out.println("Folder has been deleted");
  }

  static void listFiles(String pathname)
  {
    // определяем объект для каталога
    File dir = new File(pathname);
    // если объект представляет каталог
    if (dir.isDirectory()) {
      // получаем все вложенные объекты в каталоге
      for (File item : dir.listFiles()) {

        if (item.isDirectory()) {

          System.out.println(item.getName() + "  \t folder");
        } else {

          System.out.println(item.getName() + "\t file");
        }
      }
    }

  }
}
