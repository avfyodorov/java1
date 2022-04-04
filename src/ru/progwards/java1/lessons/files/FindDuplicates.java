package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.*;

public class FindDuplicates
{
  //  В заданном каталоге и его подкаталогах найти файлы, точно совпадающие по названию (и расширению),
//  дате-времени последнего изменения, размеру и по содержимому.
  public List<List<String>> findDuplicates(String startPath)
//  , результат - список, содержащий списки строк с именами и полными путями совпадающих файлов.
  {
//рабочая коллекция
    Map<String, TreeSet<String>> worklist = new TreeMap<>();

    try
    {
//собрать все файлы
      List<AttrFile> fullList = makeFullList(startPath);

//по каждому файлу искать дубликаты
      for (int i = 0; i < fullList.size(); i++)
      {
        for (int j = 0; j < fullList.size(); j++)
        {
//исключить проверку самим собой
          if (i == j) continue;
//сравнить файлы
          if (fullList.get(i).isDuplicate(fullList.get(j)))
          {
//есть ли уже в рабочей коллекции
            if (worklist.containsKey(fullList.get(i).path.getFileName().toString()))
            {
              TreeSet<String> treeSet = worklist.get(fullList.get(i).path.getFileName().toString());
              treeSet.add(fullList.get(i).path.toString());
              treeSet.add(fullList.get(j).path.toString());
            } else
            {
//нет такого - добавить оба
              TreeSet<String> setFiles = new TreeSet<>();
              setFiles.add(fullList.get(i).path.toString());
              setFiles.add(fullList.get(j).path.toString());
              worklist.put(fullList.get(i).path.getFileName().toString(), setFiles);
            }
          }
        }
      }
//собрать выходную структуру
      List<List<String>> res = new ArrayList<>();
      for (TreeSet<String> list : worklist.values())
      {
        res.add(new ArrayList<>(list));
      }

      return res;

    } catch (IOException e)
    {
      throw new UncheckedIOException(e);
    }
  }

  void print(List<List<String>> dup)
  {
    System.out.println("--------------------");
    for (List<String> list : dup)
    {
      for (String item : list)
      {
        System.out.println(item);
      }
      System.out.println("--------------------");
    }
  }

  public static void main(String[] args) //throws IOException
  {
    FindDuplicates fd = new FindDuplicates();
    List<List<String>> dup = fd.findDuplicates("./resources/test_dir");
    fd.print(dup);
  }

  private List<AttrFile> makeFullList(String startPath) throws IOException
  {
    List<AttrFile> res = new ArrayList<>();

    Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>()
      {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
        {
//собрать параметры файла и добавить в коллекцию
          res.add(AttrFile.create(file));
          return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc)
        {
          return FileVisitResult.CONTINUE;
        }
      }
    );
    return res;
  }
}

class AttrFile
{
  Path path;
  FileTime time;
  String txt = "";
  long size;

  String getName()
  {
    return path.getFileName().toString().toUpperCase();
  }

  public String getTxt() throws IOException
  {
    if (txt.isEmpty()) txt = Files.readString(path);
    return txt;
  }

  public boolean isContent(AttrFile file) throws IOException
  {
    return getTxt().compareTo(file.getTxt()) == 0;
  }

  public boolean isDuplicate(AttrFile file)
  {
    try
    {
      return
//файлы д.б. разными
//              (!Files.isSameFile(path, file.path)) &&
//имена совпадают
        (getName().compareTo(file.getName()) == 0) &&
//размеры
          (size == file.size) &&
//время
          (time.compareTo(file.time) == 0) &&
//content
          (isContent(file))
        ;
    } catch (IOException e)
    {
      return false;
    }
  }

  public static AttrFile create(Path path)
  {
    try
    {
      return new AttrFile(path);
    } catch (IOException e)
    {
      return null;
    }
  }

  private AttrFile(Path path) throws IOException
  {
    this.path = path;
    size = path.toFile().length();
    time = (FileTime) Files.getAttribute(path, "basic:lastModifiedTime");
  }
}