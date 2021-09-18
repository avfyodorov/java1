package examples;

import java.util.Comparator;
import java.util.TreeMap;

public class Array_Test {
   static final int size = 3;
   public static void main(String[] args) {
      Array_Test mane = new Array_Test();
      String[] params = new String[size];

      mane.toArray(params);
      for (String s : params)
         System.out.println(s);
   }

   public void toArray(String[] array) {
      array = new String[size];
      array[0] = "abc";
      array[1] = "def";
      array[2] = "ghj";
   }
   public static String removeChar(String str, char ch) {
      if (str == null)
         return null;
      return str.replaceAll(Character.toString(ch), "");
   }
}
