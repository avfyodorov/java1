package misc.alexandr320.generics;

import java.util.Arrays;

public class ArraySort {
   public static void main(String[] args) {
      int[] arr = {8, 4, 5, 2, 3, 9, 6};
      System.out.println("До сортировки: ");
      for (int j : arr) {
         System.out.print(j + " ");
      }
      arrSort(arr);
      System.out.println();
      System.out.println("После сортировки: ");
      String arrString = Arrays.toString(arr);  // превращение массива в строку
      System.out.println(arrString);
      System.out.println(Arrays.toString(arr));  // вывод на экран массива корректно

      System.out.println("До сортировки");
      Integer[] arr1 = {1, 2, 8, 4, 1, 3};
      String[] arr2 = {"b", "a", "d", "c"};
      System.out.println(Arrays.toString(arr1));
      System.out.println(Arrays.toString(arr2));
      sort(arr1);
      sort(arr2);
      System.out.println("После сортировки");
      System.out.println(Arrays.toString(arr1));
      System.out.println(Arrays.toString(arr2));
   }

   public static <T extends Comparable> void sort(T[] arr) { // сортировка массива generic пузырьком
      for (int i = 0; i < arr.length; i++) {
         for (int j = i + 1; j < arr.length; j++) {
            if (arr[i].compareTo(arr[j]) > 0) {
               T tmp = arr[i];
               arr[i] = arr[j];
               arr[j] = tmp;
            }
         }
      }
   }

   public static void arrSort(int[] arr) {    // сортировка массива int пузырьком
      for (int i = 0; i < arr.length; i++) {
         for (int j = i + 1; j < arr.length; j++) {
            if (arr[i] > arr[j]) {
               int tmp = arr[i];
               arr[i] = arr[j];
               arr[j] = tmp;
            }
         }
      }
   }
}