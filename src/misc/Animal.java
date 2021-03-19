package misc;

public class Animal {
   double weight;

   public Animal(double weight) {
      this.weight = weight;
   }

   enum AnimalKind {
      ANIMAL, COW, HAMSTER, DUCK
   }

   public AnimalKind getKind() {
      return AnimalKind.ANIMAL;
   }

   enum FoodKind {
      UNKNOWN, HAY, CORN
   }

   public FoodKind getFoodKind() {
      return FoodKind.UNKNOWN;
   }

   public String toString() {
      String zver = "I am" + getKind() + ", eat" + getFoodKind();
      //     System.out.println( zver);
      return zver;
//        return "I am" + getKind() + ", eat"+ getFoodKind();
   }

   public static void main(String[] args) {
      Animal a = new Animal(0.7);
      System.out.println(a);
   }
}
