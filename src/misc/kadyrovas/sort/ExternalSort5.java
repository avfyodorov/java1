package misc.kadyrovas.sort;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.Exchanger;

public class ExternalSort5 {
   static final String PATH_DIRECTORY = "";//"d:/own/sorts/data/";
   static final int ARRAY_SIZE = 10_000;

   static int countFiles = 0;
   static int[] ar = new int[ARRAY_SIZE];
   static int countArray = 0;

   static class DeleteTempFiles implements Runnable{
      int start;
      int finis;
      DeleteTempFiles(int start, int finis){
         this.start = start;
         this.finis = finis;
      }

      @Override
      public void run() {
         //Удаляет временные файлы отдельным процессом
         String fileName;
         Path path;
         for (int i = start; i <= finis; i++) {
            fileName = PATH_DIRECTORY + "temp" + String.valueOf(i) + ".txt";
            path = Paths.get(fileName);
            try {
               Files.deleteIfExists(path);
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
   }

   static void sort(String inFileName, String outFileName) {
      try {
         BufferedReader reader = new BufferedReader(new FileReader(inFileName));
         reader.lines().forEach(value -> {
            try {
               addArray(Integer.valueOf(value), false);
            } catch (IOException e) {
               e.printStackTrace();
            }
         });
         if (countArray > 0)
            addArray(0, true);
         reader.close();
         mergeFiles(0, countFiles - 1);
         Path path = Paths.get(PATH_DIRECTORY + "temp" + String.valueOf(countFiles) + ".txt");
         Path pathOut = Paths.get(outFileName);
         Files.move(path, pathOut, StandardCopyOption.REPLACE_EXISTING);
      } catch (IOException | InterruptedException e) {
         e.printStackTrace();
      }
   }

   static void addArray(int value, boolean notFull) throws IOException {
      if (!notFull) ar[countArray++] = value;
      if (countArray == ARRAY_SIZE || notFull) {
         Hoare.sortHoare(ar, 0, countArray - 1);
         writeSort(ar, countArray);
         countArray = 0;
      }
   }

   static void writeSort(int[] ar, int arraySize) throws IOException {
      //Запись отсортированных массивов в соответствующие временные файлы
      String tempOutputFile = PATH_DIRECTORY + "temp" + String.valueOf(countFiles++) + ".txt";
      FileOutputStream fos = new FileOutputStream(new File(tempOutputFile));
      PrintWriter tempFile = new PrintWriter(fos);

      for (int i = 0; i < arraySize; i++)
         tempFile.println(ar[i]);
      tempFile.close();
   }

   static void mergeFiles(int start, int finis) throws IOException, InterruptedException {
      int countLocal = 0; //счетчик открытых файлов
      int countAll = start; //счетчик всех файлов
      int overSize = 0; // счетчик добавленных временных файлов

      Path path;
      String fileName;
      BufferedReader[] readers = new BufferedReader[ARRAY_SIZE];
      while (true) {
         while (countAll <= finis) {
            fileName = PATH_DIRECTORY + "temp" + String.valueOf(countAll++) + ".txt";
            readers[countLocal++] = new BufferedReader(new FileReader(fileName));
            if (countLocal == ARRAY_SIZE || countAll > finis) {
               merge(readers, countLocal, overSize++);
               countLocal = 0;
            }
         }
         Thread thread = new Thread (new DeleteTempFiles(start, finis));
         thread.start();

         if (overSize > 1) {
            countFiles += overSize;
            mergeFiles(finis + 1, finis + overSize);
            return;
         }
         else
            break;
      }
   }

   static void merge(BufferedReader[] readers, int size, int overSize) throws IOException, InterruptedException {
      Exchanger<Integer> exchanger = new Exchanger();
      Thread thread = new Thread(new ThreadExchangeData(exchanger, readers, size));
      thread.start();

      Integer value;
      String tempOutputFile = PATH_DIRECTORY + "temp" + String.valueOf(countFiles + overSize) + ".txt";
      FileOutputStream fos = new FileOutputStream(new File(tempOutputFile));
      PrintWriter tempFile = new PrintWriter(fos);

      while (true) {
         value = exchanger.exchange(null);
         if (value == null)
            break;
         tempFile.println(value);
      }
      tempFile.close();
   }

   public static void main(String[] args)  {
      String inFileName = PATH_DIRECTORY + "data.txt";
      String outFileName = PATH_DIRECTORY + "SortData.txt";
      long time = System.currentTimeMillis();
      sort(inFileName, outFileName);
      System.out.println("Время сортировки: " + (System.currentTimeMillis() - time) + " мс.");
   }
}


