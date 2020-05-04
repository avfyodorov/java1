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
    Map<String,List<String>> worklist = new TreeMap<>();

    try
    {
//собрать все файлы
      Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>()
        {
          @Override
          public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
          {
//если такой файл есть - сравнивать аттрибуты
            if (worklist.containsKey(file.getFileName().toString()))
            {
              String samplefile=worklist.get(file.getFileName().toString()).get(0);
//собрать аттрибуты и сравнить
              AttrFile               attrSample=AttrFile.create(Path.of(samplefile));
              AttrFile attrFile=AttrFile.create(file);
              if (attrSample.isDuplicate(attrFile))
              {
//если совпадают - придется добавлять
                worklist.get(file.getFileName().toString()).add(file.toString());
              }
            }
            else {
//нет такого файла - добавить
              List<String> pathlist=new ArrayList<>();
              pathlist.add(file.toString());
              worklist.put(file.getFileName().toString(),pathlist);
            }
            return FileVisitResult.CONTINUE;
          }

          @Override
          public FileVisitResult visitFileFailed(Path file, IOException exc)
          {
            return FileVisitResult.CONTINUE;
          }
        }

      );

//собрать выходную структуру
      List<List<String>> res = new ArrayList<>();

      for (List<String> list:worklist.values()     )
      {
//есть одинаковые файлы
        if (list.size()>1)
          res.add(list);
      }

      return res;

    }catch (IOException e)
    {
      throw new UncheckedIOException(e);
    }
  }
  public List<List<String>> findDuplicates0(String startPath) //throws IOException
//  , результат - список, содержащий списки строк с именами и полными путями совпадающих файлов.
  {
    List<List<String>> dup = new ArrayList<>();
    try
    {
//собрать все файлы
      List<Path> fullList = makeTree(startPath);
//
      for (Path path : fullList)
      {
//        System.out.println(path);
//найти дубликаты
        List<String> itemList = duplList(startPath,path);
//если нашли дубл. - добавить
        if (itemList.size() > 0)
        {
          itemList.add(0, path.toString());
          dup.add(itemList);
        }
      }
      return dup;
    }catch (IOException e)
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
    List<List<String>> dup = fd.findDuplicates("c:/lib/java/ttt");
    fd.print(dup);
  }

  //для файла item найти дубликаты
  private List<String> duplList(String startPath,Path path) throws IOException
  {
    List<String> res = new ArrayList<>();
    PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/" + path.getFileName());
//собрать параметры
    AttrFile attrFile = AttrFile.create(path);
    if (attrFile == null) return res;

    Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>()
            {
              @Override
              public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
              {
                if (pathMatcher.matches(file))
                {
                  AttrFile cur = AttrFile.create(file);
                  if (cur != null && attrFile.isDuplicate(cur))
                    res.add(file.toString());
                }
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

  private List<Path> makeTree(String startPath) throws IOException
  {
    TreeSet<Path> res = new TreeSet<>();

    Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>()
            {
              @Override
              public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
              {
                res.add(file.getFileName());
                return FileVisitResult.CONTINUE;
              }

              @Override
              public FileVisitResult visitFileFailed(Path file, IOException exc)
              {
                return FileVisitResult.CONTINUE;
              }
            }

    );
    return new ArrayList<>(res);
  }

  private List<Path> makeFullList(String startPath) throws IOException
  {
    List<Path> res = new ArrayList<>();

    Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>()
      {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
        {
          res.add(file);
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