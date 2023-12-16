package ru.progwards.java2.lessons.recursion;

public class AsNumber2 {

   private static final String EQUALS_ICON = " = ";
   private static final String PLUS_ICON = "+";

   public static String asNumbersSum(int number) {
      return number + findCombination(number - 1, 1, "");
   }

   // curN - Текущее число, которое пытаемся разложить.
   // low  - Нижняя граница.
   public static String findCombination(int curN, int low, String str) {

      return
              (curN <= 0 ? "" :
                      (low > curN ?
                            findCombination(curN, low - curN, str + curN + PLUS_ICON)
                          : EQUALS_ICON + str + curN + PLUS_ICON + low
                            + findCombination(low - 1, 1, str + curN + PLUS_ICON)
                      )
                      + findCombination(curN - 1, low + 1, str)
              );
   }
/*
   public static String findCombination(int N, int i, String str) {
      return
              (N <= 0 ? "" :
                      (i > N ? findCombination(N, i - N, str + N + PLUS_ICON)
                              : EQUALS_ICON + str + N + PLUS_ICON + i
                              + findCombination(i - 1, 1, str + N + PLUS_ICON)
                      )
                              + findCombination(N - 1, i + 1, str)
              );
   }
*/
   public static void main(String[] args) {
        System.out.println(asNumbersSum(2));

//      System.out.println(asNumbersSum(-1));
//      System.out.println(asNumbersSum(0));
//      System.out.println(asNumbersSum(1));
//      System.out.println(asNumbersSum(2));
//      System.out.println(asNumbersSum(3));
//      System.out.println(asNumbersSum(4));
//      System.out.println(asNumbersSum(5));
//      System.out.println(asNumbersSum(6));
//      System.out.println(asNumbersSum(7));
//      System.out.println(asNumbersSum(8));
//      System.out.println(asNumbersSum(9));
//      System.out.println(asNumbersSum(10));
   }
}

