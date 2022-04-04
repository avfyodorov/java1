package ru.progwards.java1.lessons.classes;

/**
 * Автор: Фёдоров Александр
 * Дата:  14.03.2022  14:47
 */
public class CountTest {
   public static void testInc(int count){
      Count cnt = new Count();
      int i_ = -1;
      for(int i = 0; i < count - 1; i++){
         cnt.inc();
         System.out.print(cnt.getCount() + " ");
         i_ = i;
      }
      if(i_ != -1)
         System.out.println(i_ + 2);
      System.out.println("тест inc окончен");
   }

   public static void testDec(int count){
      Count cnt = new Count(count);
      int i_ = -1;
      for(int i = 0; i < count; i++){
         if(cnt.dec()){
            if(i_ != -1)
               System.out.println(cnt.getCount());
            System.out.println("count равен 0");
            break;
         }else{
            i_ = i;
            System.out.print(cnt.getCount() + " ");
         }
      }
      if(count <= 0)
         System.out.println(count-1);
      System.out.println("тест dec окончен");
   }

   public static void main(String[] args) {
      testInc(7);
      testInc(0);
      testInc(-1);
      testDec(9);
      testDec(0);
      testDec(-5);

   }
}
