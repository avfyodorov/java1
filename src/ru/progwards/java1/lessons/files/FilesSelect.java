package ru.progwards.java1.lessons.files;
//Реализовать метод с сигнатурой
// public void selectFiles(String inFolder, String outFolder, List<String> keys)
// , который сортирует файлы по их содержимому.
// Нужно просмотреть содержимое всех файлов, с расширением txt, содержащихся в каталоге inFolder
// с подкаталогами, и если файл содержит ключевое слово из коллекции keys, то скопировать его в подпапку
// с соответствующим именем, этого элемента keys, все подпапки должны находиться в outFolder.

//     Например, вызвана функция с параметрами (“aaa”, “bbb”, {“check”, “files”} )

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;

//   нужно проверить каталог aaa с подкаталогами, найти там все файлы txt,
//   и если файл содержит “check”, скопировать его в папку bbb/check,
//   если файл содержит “files”, скопировать его в папку bbb/files.
//   Важно! Если, например, слово “files” ни разу не встретилось, пустую папку создавать не нужно
public class FilesSelect {
   public void selectFiles(String inFolder, String outFolder, List<String> keys) //throws IOException
   {
      try {
         PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.txt");
         Files.walkFileTree(Paths.get(inFolder), new SimpleFileVisitor<>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                       if (pathMatcher.matches(file)) {
                          String txt = Files.readString(file);
                          for (String k : keys) {
                             if (txt.toUpperCase().contains(k.toUpperCase())) {
                                Path path = Paths.get(outFolder + "/" + k);
                                if (Files.notExists(path))
                                   Files.createDirectory(path);
                                Files.copy(file, Paths.get(path + "/" + file.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                             }
                          }
                       }
                       return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) {
                       return FileVisitResult.CONTINUE;
                    }
                 }

         );
      } catch (IOException e) {
         throw new UncheckedIOException(e);
      }
   }

   public static void main(String[] args)// throws IOException
   {
      FilesSelect fs = new FilesSelect();
      fs.selectFiles("dir", "dirout", Arrays.asList("111", "222", "333", "123"));
   }
}
//  В метод передан список ключевых слов, содержащий: 111, 222, 333, 123.
//        В каталоге inFolder располагается структура файлов (в скобках указаны содержащиеся в файлах ключевые слова):
//        dir1/file1.txt(111), dir1/file2.txt(111), dir1/file3.txt(123), dir2/dir3/file1.txt(111), dir2/dir3/file3.txt(123), dir2/file1.txt(222), dir2/file2.txt(111), file2.txt(111)
//        В каталоге outFolder обнаружена структура файлов:
//
//        Ожидалось:
//        111/file1.txt, 111/file2.txt, 123/file3.txt, 222/file1.txt
