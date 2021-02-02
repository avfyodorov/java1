package misc.dzen77.h4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Test2 {
   public static void main(String[] args) {

      Collection<Integer> n = new ArrayList<>();
      n.add(1); n.add(123);
      for (Integer i: n ) {
         System.out.println(i);
      }


      int x = 120;
      String deiString;
      String remeinderInfo = "Число оканчивается на ";

      int remeinder10 = x % 10;
      if (remeinder10 == 1) {
         deiString = "день";
      } else {
         if (remeinder10 >= 2 && remeinder10 <= 4) {
            deiString = "дня";
         } else {
            deiString = "дней";
         }
      }
      remeinderInfo += remeinder10;
      System.out.println("Событие наступит через " + x + " " + deiString);
      System.out.println(remeinderInfo);
   }
}