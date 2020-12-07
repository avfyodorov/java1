package ru.progwards.java2.lessons.generics;

        import java.util.ArrayList;
        import java.util.List;

public class FruitBox2<T extends FruitBox2> {

   private List<T> fruitsList;

   public FruitBox2() {
      fruitsList = new ArrayList<>();
   }

   void add(T... item) {
      for (int i = 0; i < item.length; i++) {
         fruitsList.add(item[i]);
      }
   }

   float getWeight() {
      float weight = (float) 0.0;
      for (int i = 0; i < fruitsList.size(); i++) {
         weight += fruitsList.get(i).getWeight();
      }
      return weight;
   }

   void moveTo(FruitBox2 box) {
      if (this.fruitsList.isEmpty()) {
         System.out.println("Невозможно взять фрукты из пустой корзины!");
         return;
      }

      if (this.equals(box)) {
         System.out.println("Невозможно перенести фрукты из корзины в ту же самую корзину!");
         return;
      }

      try {
         try {
            if (this.getElement().getClass().equals(box.getElement().getClass())) {
               displaceFruitsTo(box);
            } else {
               throw new UnsupportedOperationException();
            }
         } catch (IndexOutOfBoundsException e) {
            System.out.println("Корзина " + box + " пустая!");
         }
      } catch(UnsupportedOperationException e) {
         System.out.println("Несоответствие типов корзины!");
      }
   }

   int compareTo(FruitBox2 box) {
      if (this.getWeight() > box.getWeight())
         return 1;
      if (this.getWeight() < box.getWeight())
         return -1;
      return 0;
   }

   void displaceFruitsTo(FruitBox2 box) {
      for (int i = 0; i < this.fruitsList.size(); i++) {
         box.add(this.fruitsList.get(i));
      }
      this.fruitsList.clear();
   }

   T getElement() {
      T elem = fruitsList.get(0);
      return elem;
   }

}

class Apple2 extends FruitBox2 {
   private float weight;

   public Apple2() {
      weight = 1.0f;
   }

   public float getWeight() {
      return weight;
   }
}

class Orange2 extends FruitBox2 {
   private float weight;

   public Orange2() {
      weight = 1.5f;
   }

   public float getWeight() {
      return weight;
   }
}
