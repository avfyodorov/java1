package misc.ansemal.classloader;


import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PathLoader extends ClassLoader{
   final static String ROOT = "c:\\data\\";
   final static String DOT_CLASS = ".class";
   private static PathLoader loader = new PathLoader();
   private static String dateRoot;   // путь = ROOT + папка с датой обновления

   public PathLoader () {
      this(ClassLoader.getSystemClassLoader());
   }

   public PathLoader (ClassLoader parent) {
      super(parent);
   }

   @Override
   public Class<?> loadClass(String name) throws ClassNotFoundException {
      return loadClass(name, true);
   }

   @Override
   protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
      Class<?> c = findClass(name);
      if (resolve)
         resolveClass(c);
      return c;
   }

   @Override
   protected Class<?> findClass(String name) throws ClassNotFoundException {
      try {
         String classPath = name.replace(".", "\\");
         Path classPathName = Paths.get(dateRoot + classPath + DOT_CLASS);
         if (Files.exists(classPathName)) {
            byte [] b = Files.readAllBytes(classPathName);
            return defineClass(name, b, 0 , b.length);
         } else
            return findSystemClass(name);
      } catch (IOException ex) {
         throw new ClassNotFoundException(name);
      }
   }

   private static void updateClass (Map<String,String> whenModif, Map<String,Object> task) throws IOException {
      ArrayList<String> date = new ArrayList<>();   // список дат обновления

      // проходим с глубиной 1 для создания списка дат обновлений
      Files.walkFileTree(Paths.get(ROOT), Collections.emptySet(), 1, new SimpleFileVisitor<>() {
         String nameDir;
         @Override
         public FileVisitResult visitFile(Path file, BasicFileAttributes attrs){
            if (Files.isDirectory(file)) {
               nameDir = file.getFileName().toString();
               // если в имени папки 8 цифр
               if (nameDir.matches("\\d+") && nameDir.length() == 8)
                  date.add(nameDir);
            }
            return FileVisitResult.CONTINUE;
         }
      });
      Collections.sort(date);
      // перибираем даты обновлений по убыванию
      for (int i = date.size()-1; i>=0; i--) {
         String dateUpdate = date.get(i);
         dateRoot = ROOT  + date.get(i) + "\\";
         Files.walkFileTree(Paths.get(dateRoot), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
               // если находим в обновлениях файл .class
               if (path.toString().endsWith(DOT_CLASS)) {
                  String className = makeClassName (path);                             // получаем его полное имя с пакетом
                  String lastUpdate = whenModif.get(className);                        // получаем последнюю дату обновления или null если не обновлялся
                  if (lastUpdate == null || lastUpdate.compareTo(dateUpdate) < 0) {    //  можно поменять на интеджер
                     FileWriter writer = new FileWriter("patchloader.log", true);
                     try  {
                        if (lastUpdate != null)
                           loader = new PathLoader();                               // если уже обновлялся- создаем новый экземпляр загрузчика
                        Class<?> updateClass = loader.loadClass(className, true);   // загружаем обновление
                        whenModif.put(className, dateUpdate);                              // записываем дату обновления
                        writer.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss"))
                                + " " + className + " загружен из " + dateRoot + " успешно.\n");
                        // следующие 2 строки необязательны - создаем объект этого класса - просто для проверки работоспособности программы в main
                        Object object = updateClass.getDeclaredConstructor().newInstance();
                        task.put(className, object);
                     } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                             InvocationTargetException | NoSuchMethodException ex ) {
                        writer.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss"))
                                + " " + className + " ошибка загрузки " + ex.getMessage() + "\n");
                        ex.printStackTrace();
                     }
                  }
               }
               return FileVisitResult.CONTINUE;
            }
         });
      }
   }

   private static String makeClassName(Path path) throws IOException {
      path = path.toAbsolutePath().toRealPath();
      Path relPath = Paths.get(dateRoot).relativize(path);
      String className = relPath.toString().replaceAll("[\\\\]", ".");
      if (className.toLowerCase().endsWith(DOT_CLASS)) {
         className = className.substring(0, className.length()-DOT_CLASS.length());
      }
      return className;
   }

   public static void main(String[] args) {
      Map<String,String> whenModif = new HashMap<>();     // полное имя класса/последняя дата обновления
      Map<String,Object> classAndObject = new HashMap<>();          // полное имя класса/объект этого класса - для проверки работоспособности

      while (true) {
         try {
            Thread.sleep(1000);                   // задержка - для простоты восприятия
            System.out.println("Проверка обновления " + String.format("%1$tI:%1$tM:%1$tS.%1$tN", new Date()));
            updateClass(whenModif, classAndObject);

            // цикл для проверки работоспособности загрузчика - запускает нестатические методы без параметров и выводит поля обновленных методов
            for (var clazz: classAndObject.entrySet()) {
               Method [] methods = clazz.getValue().getClass().getDeclaredMethods();
               for (Method m:methods )
                  if (!Modifier.isStatic(m.getModifiers()))
                     m.invoke(clazz.getValue());
               Field [] fields = clazz.getValue().getClass().getDeclaredFields();
               for (Field field:fields)
                  System.out.println(field.get(clazz.getValue()));
            }
         } catch (Exception ex) {
            ex.printStackTrace();
         }
         System.out.println();
      }
   }
}

