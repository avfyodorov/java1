package misc.gesiod.files;


        import java.nio.file.*;
        import java.nio.file.attribute.BasicFileAttributes;
        import java.nio.file.attribute.FileTime;
        import java.util.ArrayList;
        import java.util.Collection;
        import java.util.LinkedList;
        import java.util.List;
        import java.io.File;
        import java.io.IOException;

public class FindDuplicates {
   List<Path> allFiles = new LinkedList<>(); // список с путями всех файлов с текущего каталога

   public List<List<String>> findDuplicates(String startPath) throws IOException {
      PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**");

      Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>(){
         @Override
         public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (pathMatcher.matches(file))
               allFiles.add(file); // добавляем в список пути ко всем файлам
            return FileVisitResult.CONTINUE;
         }

         @Override
         public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
         }
      });
      return duplicatesSearch(allFiles);
   }
   // узнать расширение файла
   static String fileExpansion(Path fileName){
      String expansion = String.valueOf(fileName);
      int index = expansion.lastIndexOf(".");
      return expansion.substring(index + 1);
   }

   // найти дубликаты
   static List<List<String>> duplicatesSearch (List<Path> pathList) throws IOException {
      List<List<String>> allDuplicates = new ArrayList<>();
      while (pathList.size() > 1){
         List<String> fileInfo = new ArrayList<>(); // список для записи одинаковых файлов
         fileInfo.clear();
         for (int i = 1; i < pathList.size(); i++) {
            if (fileInfo.size() == 0){ // добавляем файл из начала pathList и сравниваем с ним другие из остального списка
               fileInfo.add(String.valueOf(pathList.get(0).getFileName()) + " " + pathList.get(0).toAbsolutePath());
            }
            if (pathList.get(0).getFileName().equals(pathList.get(i).getFileName()) &&
                    fileExpansion(pathList.get(0)).equals(fileExpansion(pathList.get(i))) &&
                    Files.getLastModifiedTime(pathList.get(0)).equals(Files.getLastModifiedTime(pathList.get(i))) &&
                    Files.size(pathList.get(0)) == Files.size(pathList.get(i)) &&
                    Files.readString(pathList.get(0)).equals(Files.readString(pathList.get(i)))){
               // если дубликаты есть, то добавляем в fileInfo имя файла и полный путь
               fileInfo.add(String.valueOf(pathList.get(i).getFileName()) + " " + pathList.get(i).toAbsolutePath() + "\n");
               pathList.remove(i);
               i--;
            }
         }
         allDuplicates.add(fileInfo);
         pathList.remove(0);
      }
      // удаляем из списка списков все файлы, у которых нет дубликатов
      for (int i = 0; i < allDuplicates.size(); i++) {
         if (allDuplicates.get(i).size() < 2){
            allDuplicates.remove(i);
            i--;
         }
      }
      return allDuplicates;
   }


   public static void main(String[] args) throws IOException {
      FindDuplicates findDuplicates = new FindDuplicates();
      final String HOME_DIR = "C:/Users/Марина/IdeaProjects/HelloWorld/test/";
      String text = "Every day I'm shuffling";
//        Path pathForCreate = Files.createDirectories(Paths.get(HOME_DIR + "path1/path2/path3"));
      Path path = Paths.get(HOME_DIR + "path1/path2/path3");
      Path file1 = Files.writeString(path.resolve("file1.txt"), text);
//        Files.copy(file1, path.resolve("file2.txt"));
//        Path file3 = Files.writeString(path.getParent().getParent().resolve("file3.txt"), text);
//        Path file2 = Files.writeString(path.getParent().getParent().resolve("file2.txt"), text);
//        Path fileNew1 = Files.writeString(path.getParent().getParent().resolve("file1.txt"), text + " budy");
//        Path file1InPath1 = Files.writeString(path.getParent().getParent().getParent().resolve("file1.txt"), text );
//        Path file3InPath1 = Files.writeString(path.getParent().getParent().getParent().resolve("file3.txt"), text);
      PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**");
      Files.walkFileTree(Paths.get(HOME_DIR), new SimpleFileVisitor<>(){
         @Override
         public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (pathMatcher.matches(file))
               Files.setLastModifiedTime(file, Files.getLastModifiedTime(Paths.get(HOME_DIR + "path1/path2/path3/file1.txt")));
//                    System.out.println(file.getFileName());
//                    System.out.println(Files.getLastModifiedTime(file));
//                    System.out.println(Files.getAttribute(file, "size"));
//                    System.out.println(fileExpansion(file));
//                    System.out.println();
            return FileVisitResult.CONTINUE;
         }

         @Override
         public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
         }
      });
      System.out.println(findDuplicates.findDuplicates(HOME_DIR));
//        System.out.println(findDuplicates.allFiles.size());
   }
}

