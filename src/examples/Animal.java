package examples;

public class Animal {

   public Animal eat() {
      System.out.println("animal eat");
      return null;
   }

   public Long calc() {
      return null;
   }

   public static void main(String[] args) {
      Animal dog=new Dog();

   }
}
 class Dog extends Animal {
public void s1(){
   System.out.println("s1");
}
   public Dog eat() {
      return new Dog();
   }
/*attempting to use incompatible return type
    public Integer calc() {
        return null;
    }
*/
}