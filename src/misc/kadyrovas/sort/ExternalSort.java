package misc.kadyrovas.sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;

//В 1-м классе читается исходный файл со случайными числами по 10_000 чисел,
// сортируется и в первый раз записывается во временный файл, в дальнейшем
// отсортированные 10_000 чисел и числа из временного файла методом слияния
// формируют другой временный файл, затем предыдущий файл стирается. Так
// продолжается пока исходный файл со случайными числами не закончится. Но это ОЧЕНЬ ДОЛГО.

public class ExternalSort{
   static final int ARRAY_SIZE = 10_000;
   static int countPath = 0;
   static void sort(String inFileName, String outFileName) {
      int[] ar = new int[ARRAY_SIZE];
      String randomValue;
      int count = 0;
      try (BufferedReader reader = new BufferedReader(new FileReader(inFileName))) {

         //зачитать 10000 чисел в массив
         while ((randomValue = reader.readLine()) != null) {
            ar[count ++] = Integer.valueOf(randomValue);
            //сортировать
            if (count == ARRAY_SIZE) {
               sortHoare(ar, 0, count - 1);
               mergeSort(ar, count);
               count = 0;
            }
         }
         if (count > 0) {
            sortHoare(ar, 0, count - 1);
            mergeSort(ar, count);
         }

         Path path = Paths.get("temp" + String.valueOf(countPath) + ".txt");
         Path pathResult = Paths.get(outFileName);
         Files.move(path, pathResult, StandardCopyOption.REPLACE_EXISTING);
      }
      catch (IOException e) {
         e.printStackTrace();
      }
   }

   public static void sortHoare(int[] a, int left, int right) {
      if (left < right) {
         int pivot = hoare(a, left, right);
         sortHoare(a, left, pivot);
         sortHoare(a, pivot + 1, right);
      }
   }

   public static int hoare(int[] a, int left, int right) {
      int pivot = a[(left+right) / 2];
      int i = left - 1;
      int j = right + 1;
      for(;;) {
         do {
            i++;
         } while (a[i] < pivot);

         do {
            j--;
         } while (a[j] > pivot);

         if (i >= j)
            return j;

         swap(a, i, j);
      }
   }

   static void swap(int [] a, int i, int j) {
      int tmp = a[i];
      a[i] = a[j];
      a[j] = tmp;
   }

   static void mergeSort(int[] ar, int arraySize) throws IOException {
      String tempInPath = "temp" + String.valueOf(countPath++) + ".txt";
      Path pathIn = Paths.get(tempInPath);

      String tempOutPath = "temp" + String.valueOf(countPath) + ".txt";
      Path pathOut = Paths.get(tempOutPath);
      if (!Files.exists(pathOut))
         Files.createFile(pathOut);

      int count = 0;
      String stringValue;
      String stringValueArray;
      int intValue = 0;

      BufferedReader reader;
      if (Files.exists(pathIn)) {
         reader = new BufferedReader(new FileReader(tempInPath));
         stringValue = reader.readLine();
         if (stringValue != null && stringValue != "")
            intValue = Integer.valueOf(stringValue);

         while (stringValue != null && stringValue != "" && count < arraySize) {
            if (intValue < ar[count]) {
               Files.writeString(pathOut, stringValue +"\n", StandardOpenOption.APPEND);
               stringValue = reader.readLine();
               if (stringValue != null && stringValue != "")
                  intValue = Integer.valueOf(stringValue);
            } else {
               stringValueArray = String.valueOf(ar[count++]);
               Files.writeString(pathOut, stringValueArray + "\n", StandardOpenOption.APPEND);
            }
         }

         while (stringValue != null && stringValue != "") {
            Files.writeString(pathOut, stringValue + "\n", StandardOpenOption.APPEND);
            stringValue = reader.readLine();
         }
         reader.close();
      }

      while (count < arraySize) {
         stringValueArray = String.valueOf(ar[count++]);
         Files.writeString(pathOut, stringValueArray + "\n", StandardOpenOption.APPEND);
      }

      Files.deleteIfExists(pathIn);
   }

   public static void main(String[] args) throws IOException {
      String inFileName = "d:/Own/Sorts/Data/Data.txt";
      String outFileName = "d:/Own/Sorts/Data/SortData.txt";
      sort(inFileName, outFileName);
   }
}
