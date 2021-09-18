package misc.vova777.generics;

//1. Реализовать классы Fruit и потомки Apple, Orange;
//2. Реализовать класс FruitBox, в который можно складывать фрукты как потомок ArrayList.
//3. Обратить внимание на метод добавления фрукта в коробку - add. Каждая коробка может содержать фрукты
//   только одного типа, либо я блоки, либо апельсины.
//4.Сделать метод getWeight(), который высчитывает вес коробки. Задать вес одного фрукта: вес яблока – 1.0f,
//  апельсина – 1.5f (единицы измерения не важны); Количество считаем как количество элементов массива.
//5. Реализовать метод moveTo, который позволяет пересыпать фрукты из текущей коробки в другую, переданную
//   в качестве параметра. Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
//   Если у нас фрукты одного типа, то в текущей коробке фруктов не остается, а в другую перекидываются объекты,
//   которые были в первой; В противном случае выдать исключение UnsupportedOperationException.
//6. Реализовать метод сompareTo, который позволяет сравнить текущую коробку с переданной в качестве параметра.
//   Вернуть: 1 - если масса текущей меньше массы переданной параметром, 0 - если их массы равны и 1
//   в противоположном случае. Коробки с яблоками и апельсинами тоже можно сравнивать.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class FruitBox<E extends Fruit> extends ArrayList<E> implements Comparable<FruitBox> {
E obj;

    public float getWeight(){
        if (this.isEmpty()) return 0.0f;
        return this.size() * this.get(0).weight;
    }

    public void moveTo(FruitBox fruitBox2){
        if (!(this.isEmpty()) && !(fruitBox2.isEmpty())){
            if ((this.get(0).getClass().equals(fruitBox2.get(0).getClass()))) fruitBox2.addAll(this);
            else throw new UnsupportedOperationException("This FruitBoxes hold different kinds of fruit");
        }
        else if (fruitBox2.isEmpty()) throw new UnsupportedOperationException("Коробка в которую пересыпают " +
                "пуста, операция не имеет смысла");
        else if (this.isEmpty()) throw new UnsupportedOperationException("Коробка из которой пересыпают " +
                "пуста, операция не имеет смысла");
    }




    public static void main(String[] args) {
        Apple apple1 = new Apple();
        var apple2 = new Apple();
        Apple apple3 = new Apple();
        Apple apple4 = new Apple();
        Orange orange1 = new Orange();
        Orange orange2 = new Orange();
        Orange orange3 = new Orange();
        Orange orange4 = new Orange();
        FruitBox<Orange> fruitBox = new FruitBox();
        fruitBox.add(orange1);
        fruitBox.add(orange2);
        fruitBox.add(orange3);
        fruitBox.add(orange4);
        System.out.println(fruitBox.getWeight());
        FruitBox<Apple> fruitBox2 = new FruitBox<>();
        fruitBox2.add(new Apple());
        fruitBox.moveTo(fruitBox2);
        System.out.println(fruitBox2.size());

    }

    @Override
    public int compareTo(FruitBox fruitBox) {
        if (this.getWeight() < fruitBox.getWeight()) return 1;
        else if (this.getWeight() == fruitBox.getWeight()) return 0;
        else  return -1;
    }
}
