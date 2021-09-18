package misc.twist328.generics;

import java.util.ArrayList;
import java.util.List;

public class FruitBox {
/*
1. Реализовать классы Fruit и потомки Apple, Orange;
2. Реализовать класс FruitBox, в который можно складывать фрукты как потомок ArrayList.
3. Обратить внимание на метод добавления фрукта в коробку. Каждая коробка может содержать фрукты только одного типа, либо яблоки, либо апельсины.
4. Сделать метод getWeight(), который высчитывает вес коробки. Задать вес одного фрукта: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
Количество считаем как количество элементов массива.
5. Реализовать метод moveTo, который позволяет пересыпать фрукты из текущей коробки в другую, переданную в качестве параметра.
Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами. Если у нас фрукты одного типа, то в текущей коробке фруктов не остается,
а в другую перекидываются объекты, которые были в первой; В противном случае выдать исключение UnsupportedOperationException.
6. Реализовать метод сompareTo, который позволяет сравнить текущую коробку с переданной в качестве параметра.
Вернуть -1 – если их масса текущей меньше массы переданной параметром, 0 если их массы равны и 1 в противоположном случае.
Коробки с яблоками и апельсинами тоже можно сравнивать.
*/


    List<Fruit> fruitList = new ArrayList<>();


    public void add(Fruit fruit) {// добавить единицу фрукта

        if (fruitList.size() > 0) {
            String getToBox = fruitList.get(0).getClass().getName();// проверим соответствие сорта в ящике
            System.out.println("getToBox1=" + getToBox);
            if (fruit.getClass().getName().compareTo(getToBox) != 0)
                throw new UnsupportedOperationException();
        }
        fruitList.add(fruit);
    }
    public List<Fruit> getFruitList() {
        return fruitList;
    }

    public void setFruitList(List<Fruit> fruitList) {
        this.fruitList = fruitList;
    }

    public void add(List<Fruit> fruits) {
        Fruit sorts;
        if (fruitList.size() > 0) {
            sorts = fruitList.get(0);
        } else if (fruits.size() > 0) {
            sorts = fruits.get(0);
        } else return;
        String getToBox = sorts.getClass().getName();
        System.out.println("getToBox2=" + getToBox);

        for (Fruit fruit : fruits) {
            if (fruit.getClass().getName().compareTo(getToBox) == 0)
                fruitList.add(fruit);
        }
    }

    // пересыпать фрукты из текущей коробки в другую, переданную в качестве параметра.
    public void moveTo(FruitBox box) {
        if (fruitList.size() == 0) return;
        if (box.fruitList.size() > 0) {

            String getToBox = box.fruitList.get(0).getClass().getName();// проверим сорт
            System.out.println("getToBox3=" + getToBox);
            if (fruitList.get(0).getClass().getName().compareTo(getToBox) != 0)
                throw new UnsupportedOperationException();
        }
        box.fruitList.addAll(fruitList);
        fruitList.clear();
    }

    // вес коробки
    public double getWeight() {
        double result = 0;
        for (Fruit w : fruitList) {
            result += w.getWeight();
        }
        return result;
    }

    public static void main(String[] args) {
        Apple a1 = new Apple();
        Apple a2 = new Apple();
        Orange o1 = new Orange();
        Orange o2 = new Orange();
        FruitBox box1 = new FruitBox();
        box1.add(List.of(a2, a1, a1, o2, a2, o1, o2));
        FruitBox box2 = new FruitBox();
        box2.add(a2);
        FruitBox box3 = new FruitBox();
        box3.add(o1);
        System.out.println("box1=" + box1.getWeight());
        System.out.println("box2=" + box2.getWeight());
        System.out.println("box3=" + box3.getWeight());

        box1.moveTo(box2);
        System.out.println("box1.moveTo(box2)");

        System.out.println("box1=" + box1.getWeight());
        System.out.println("box2=" + box2.getWeight());

        box3.moveTo(box1);
        System.out.println("box3.moveTo(box1)");

        System.out.println("box1=" + box1.getWeight());
        System.out.println("box3=" + box3.getWeight());

        try {
            System.out.print("box1.moveTo(box2) - ");
            box1.moveTo(box2);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("box1=" + box1.getWeight());
        System.out.println("box2=" + box2.getWeight());
    }
}


