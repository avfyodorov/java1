package misc.kadyrovas.sort;

public class Hoare {
   public static void sortHoare(int[] a, int left, int right) {
      if (left < right) {
         int pivot = hoare(a, left, right);
         sortHoare(a, left, pivot);
         sortHoare(a, pivot + 1, right);
      }
   }

   public static int hoare(int[] a, int left, int right) {
      int pivot = a[(left + right) / 2];
      int i = left - 1;
      int j = right + 1;
      for (; ; ) {
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

   static void swap(int[] a, int i, int j) {
      int tmp = a[i];
      a[i] = a[j];
      a[j] = tmp;
   }

}
