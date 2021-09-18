package misc.vg.generics;

import java.util.ArrayList;

public class FruitBox<E extends Fruit> extends ArrayList<E> {

    public double getWeight() {
        if (this.size()==0) {
            return 0.0;
        } else {
            return this.size() * this.get(0).getWeight();
        }
    }

    public void moveTo(FruitBox fb) throws Exception {
        boolean fbIsEmpty = false;
        if (this.size()==0) {
            System.out.println("Пересыпать нечего!");
            return;
        }
        if (fb.size()==0) {
            fb.add((E) new Object());
            fbIsEmpty = true;
        }
        if (fb.get((0)).getClass().equals(this.get(0).getClass())) {
            if (fbIsEmpty) {
                fb.clear();
            }
            fb.addAll(this);
            this.clear();
        } else {
            if (fbIsEmpty) {
                fb.clear();
            }
            throw new Exception("UnsupportedOperationException");
        }
    }

    public int compareTo(FruitBox fb) {
        return Double.compare(this.getWeight(), fb.getWeight());
    }

    public static void main(String[] args) {
        FruitBox<Apple> fbA = new FruitBox<>();
        FruitBox<Apple> fbA2 = new FruitBox<>();
        FruitBox<Orange> fbO = new FruitBox<>();
        fbA.add(new Apple());
        fbA2.add(new Apple());
        fbO.add(new Orange());
        System.out.println(fbA.getWeight());
        System.out.println(fbA2.getWeight());
        System.out.println(fbO.getWeight());
        System.out.println(fbA.compareTo(fbO));

        try {
            fbA.moveTo(fbA2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(fbA.getWeight());
        System.out.println(fbA2.getWeight());
        try {
            fbA2.moveTo(fbO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
