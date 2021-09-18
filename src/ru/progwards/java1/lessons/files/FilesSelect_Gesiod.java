package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FilesSelect_Gesiod {

   List<Path> allNeedFiles = new LinkedList<>(); // список с путями всех файлов с текущего каталога

   public void selectFiles(String inFolder, String outFolder, List<String> keys){
      try {
         PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.txt");

         Files.walkFileTree(Paths.get(inFolder), new SimpleFileVisitor<>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
               if (pathMatcher.matches(file) && isContainKey(file, keys))
                  allNeedFiles.add(file);
               return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
               return FileVisitResult.CONTINUE;
            }
         });

         for (Path path : allNeedFiles){
            Path fileCopy = Paths.get(outFolder + "/" + keyWord(path, keys) + "/" + path.getFileName());
            if (Files.notExists(fileCopy.getParent())) {
               System.out.println(fileCopy.getParent());

               Files.createDirectory(fileCopy.getParent());
            }
               //                System.out.println(fileCopy.getParent().toAbsolutePath());
//            if (Files.notExists(fileCopy))
//               Files.createFile(fileCopy);
//                System.out.println(fileCopy.toAbsolutePath());
            Files.copy(path, fileCopy, StandardCopyOption.REPLACE_EXISTING);
         }

      } catch (IOException e){
         throw new UncheckedIOException(e);
      }
   }

   static boolean isContainKey (Path path, List<String> keys) throws IOException {
      for (String key : keys){
         if (Files.readString(path).contains(key)){
            return true;
         }
      }
      return  false;
   }

   static String keyWord (Path path, List<String> keys) throws IOException {
      String isKey = "";
      for (String key : keys){
         if (Files.readString(path).contains(key)){
            isKey = key;
            break;
         }
      }
      return isKey;
   }

   public static void main(String[] args) throws IOException {
      FilesSelect_Gesiod filesSelect = new FilesSelect_Gesiod();
      final String HOME_DIR = "C:/Users/Марина/IdeaProjects/HelloWorld/test";
      List<String> words = new ArrayList<>();
      words.add("111");
      words.add("222");
      words.add("333");
      words.add("123");
//        String textWth111 = "Every 111 shuffling";
//        String textWth222 = "Every 222 shuffling";
//        String textWth333 = "Every 333 shuffling";
//        String textWth123 = "Every 123 shuffling";
//        Path Dir111 = Files.createDirectories(Paths.get(HOME_DIR + "test1/111"));
//        Path Dir222 = Files.createDirectories(Paths.get(HOME_DIR + "test1/222"));
//        Path Dir333 = Files.createDirectories(Paths.get(HOME_DIR + "test1/333"));
//        Path Dir123 = Files.createDirectories(Paths.get(HOME_DIR + "test1/123"));
//        Path pathForCreateDir1 = Files.createDirectories(Paths.get(HOME_DIR + "dir1"));
//        Path pathForCreateDir2AndDir3 = Files.createDirectories(Paths.get(HOME_DIR + "dir2/dir3"));
//        Path file1inDir3 = Files.writeString(pathForCreateDir2AndDir3.resolve("file1.txt"), textWth111);
//        Path file1inDir1 = Files.writeString(pathForCreateDir1.resolve("file1.txt"), textWth111);
//        Path file2inDir1 = Files.writeString(pathForCreateDir1.resolve("file2.txt"), textWth111);
//        Path file3inDir1 = Files.writeString(pathForCreateDir1.resolve("file3.txt"), textWth123);
//        Path file3inDir3 = Files.writeString(pathForCreateDir2AndDir3.resolve("file3.txt"), textWth123);
//        Path file1inDir2 = Files.writeString(pathForCreateDir2AndDir3.getParent().resolve("file1.txt"), textWth222);
//        Path file2inDir2 = Files.writeString(pathForCreateDir2AndDir3.getParent().resolve("file2.txt"), textWth111);
//        Path file2inHome = Files.writeString(pathForCreateDir1.getParent().resolve("file2.txt"), textWth111);

//      filesSelect.selectFiles(HOME_DIR, HOME_DIR + "/test1", words);
      filesSelect.selectFiles("dir",  "dirout", words);
   }
}
