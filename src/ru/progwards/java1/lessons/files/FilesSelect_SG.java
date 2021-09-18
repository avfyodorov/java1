package ru.progwards.java1.lessons.files;

        import java.io.IOException;
        import java.nio.file.*;
        import java.nio.file.attribute.BasicFileAttributes;
        import java.nio.file.attribute.FileAttribute;
        import java.util.Arrays;
        import java.util.List;

public class FilesSelect_SG {

   public static final String PATTERN = "glob:**/*.txt";

   public static void selectFiles(String inFolder, String outFolder, List<String> keys) {

      PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(PATTERN);
      FileAttribute<?>[] fileAttributes = new FileAttribute[0];

      try {
         Path directory = Files.createDirectory(Paths.get(outFolder), fileAttributes);
         Files.walkFileTree(Paths.get(inFolder).normalize(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
               if (pathMatcher.matches(path)) {
                  try {
                     List<String> list = Files.readAllLines(path);
                     for (String string : list) {
                        String[] tmp = string.split(" ");
                        for (String str : tmp) {
                           for (String s : keys) {
                              if (s.trim().equalsIgnoreCase(str.trim())) {
                                 Path path1 = Paths.get(directory.toString() + "/" + s);
                                 if (!Files.exists(path1)) {
                                    Files.createDirectory(path1);
                                    Path filePath = Paths.get(directory.toString() + "/" + s + "/" + path.getFileName().toString());
                                    Files.copy(path, filePath);
                                 } else {
                                    if (!Files.exists(Paths.get(directory.toString() + "/" + s + "/" + path.getFileName().toString()))) {
                                       Path filePath = Paths.get(directory.toString() + "/" + s + "/" + path.getFileName().toString());
                                       Files.copy(path, filePath);
                                    }
                                 }
                              }
                           }
                        }
                     }

                  } catch (IOException e) {
                     e.printStackTrace();
                  }
               }
               return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
               return FileVisitResult.CONTINUE;
            }
         });
      } catch (IOException ex) {
         ex.printStackTrace();
      }

   }


   public static void main(String[] args) {
      selectFiles("dir", "dirout", Arrays.asList("111", "222", "333", "123"));
   }


   /* Реализовать метод
    который сортирует файлы по их содержимому. Нужно просмотреть содержимое всех файлов,
    с расширением txt, содержащихся в каталоге inFolder
    с подкаталогами, и если файл содержит ключевое слово из коллекции keys,
    то скопировать его в подпапку с соответствующим именем, этого элемента keys,
    все подпапки должны находиться в outFolder.

    Например, вызвана функция с параметрами (“aaa”, “bbb”, {“check”, “files”} )

    нужно проверить каталог aaa с подкаталогами, найти там все файлы txt,
    и если файл содержит “check”, скопировать его в папку bbb/check, если файл содержит “files”,
    скопировать его в папку bbb/files. Важно! Если, например, слово “files” ни разу не встретилось,
    пустую папку создавать не нужно*/
}
