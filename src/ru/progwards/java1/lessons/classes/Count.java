package ru.progwards.java1.lessons.classes;

/**
 * Автор: Фёдоров Александр
 * Дата:  14.03.2022  14:44
 */
public class Count {
   public Count(int count) {
      this.count = count;
   }
   public Count() {
      count = 0;
   }


   int count;

   public void inc(){
      count++;
   }

   public int getCount(){
      return count;
   }

   public boolean dec(){
      count = count - 1;
      return count <= 0;
   }

   public static void main(String[] args){
      Count cnt = new Count(10);

      while(!cnt.dec()){
      }
      System.out.println("count равен " + cnt.count);
   }
}
