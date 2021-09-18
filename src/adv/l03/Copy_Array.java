package adv.l03;

import java.util.ArrayList;

public class Copy_Array {
   //   public static <T> ArrayList from(T[] in) {
//        ArrayList<T> list = new ArrayList<>(in.length);
//        Collections.addAll(list, in);
//        return list;
//    }
   public static <T> ArrayList from(T[] in) {
      ArrayList<T> list = new ArrayList<>();
      for (int i = 0; i < in.length; i++)
         list.add(in[i]);
      return list;
   }

   public static void main(String[] args) {
      Integer[] ii = {1, 2, 3};
      ArrayList<Integer> arri = from(ii);
      System.out.println(arri);

      String[] sa = {"sa", "bg", "xc"};
      ArrayList<String> arrs = from(sa);
      System.out.println(arrs);

   }
}
