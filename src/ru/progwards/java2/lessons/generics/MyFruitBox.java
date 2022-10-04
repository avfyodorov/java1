package ru.progwards.java2.lessons.generics;

import java.util.ArrayList;

/**
 * Автор: Фёдоров Александр
 * Дата:  04.10.2022  18:23
 */
public class MyFruitBox<T extends MyFruit> extends ArrayList<T> {

    private final Class<T> typeParam;

    public Class<T> getTypeParam() {
        return typeParam;
    }

    MyFruitBox(Class<T> typeParam) {
        this.typeParam = typeParam;
    }

    //  3. Обратить внимание на метод добавления фрукта в коробку.
//  Каждая коробка может содержать фрукты только одного типа, либо я блоки, либо апельсины.
    @Override
    public boolean add(T fruit) {
        return super.add(fruit);
    }

    //.Сделать метод getWeight(), который высчитывает вес коробки. Задать вес одного фрукта:
    //    вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
    //Количество считаем как количество элементов массива
    public double getWeight() {
        return size() == 0 ? 0.0 : size() * get(0).getWeight();
    }

    //    5. Реализовать метод moveTo, который позволяет пересыпать фрукты из текущей коробки в другую,
//    переданную в качестве параметра. Помним про сортировку фруктов: нельзя яблоки высыпать в коробку
//    с апельсинами. Если у нас фрукты одного типа, то в текущей коробке фруктов не остается,
//    а в другую перекидываются объекты, которые были в первой;
//    В противном случае выдать исключение UnsupportedOperationException.
    public void moveTo(MyFruitBox destBox) throws UnsupportedOperationException {
        if (getTypeParam().equals(destBox.getTypeParam())) {
            destBox.addAll(this);
            this.clear();
        } else {
            throw new UnsupportedOperationException("Коробки разных типов");
        }
    }

    //     6. Реализовать метод сompareTo, который позволяет сравнить текущую коробку с переданной в
//     качестве параметра. Вернуть: 1 - если масса текущей меньше массы переданной параметром,
//     0 - если их массы равны и 1 в противоположном случае. Коробки с яблоками и апельсинами тоже
//     можно сравнивать.
    public int compareTo(MyFruitBox fb) {
        return Double.compare(getWeight(), fb.getWeight());
    }

    public static void main(String[] args) {
//Завести три разные корзины.
        MyFruitBox<MyApple> fbApple = new MyFruitBox<>(MyApple.class);
        MyFruitBox<MyApple> fbAppleTest = new MyFruitBox<>(MyApple.class);
        MyFruitBox<MyOrange> fbOrange = new MyFruitBox<>(MyOrange.class);

//Завести  яблоко и апесин.
        MyApple myApple = new MyApple();
        MyOrange myOrange = new MyOrange();

//        System.out.println("Добавляю апельсин в пустую коробку из-под яблок: " + fbApple.add(myOrange));
        System.out.println("Добавляю яблоко в пустую коробку из-под яблок: " + fbApple.add(myApple));
        try {
            System.out.println("Было.  Oткуда:" + fbApple.size() + " Куда: " + fbAppleTest.size());
            fbApple.moveTo(fbAppleTest);
            System.out.println("Стало. Oткуда:" + fbApple.size() + " Куда: " + fbAppleTest.size());

            System.out.println("Было.  Oткуда:" + fbAppleTest.size() + " Куда: " + fbOrange.size());
            fbAppleTest.moveTo(fbOrange);
            System.out.println("Стало. Oткуда:" + fbAppleTest.size() + " Куда: " + fbOrange.size());

            fbAppleTest.moveTo(fbOrange);
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Итого. Oткуда:" + fbAppleTest.size() + " Куда: " + fbOrange.size());
    }
}


class MyFruit {
    public double getWeight() {
        return 0.0;
    }
}

class MyOrange extends MyFruit {
    @Override
    public double getWeight() {
        return 8.12;
    }
}

class MyApple extends MyFruit {
    @Override
    public double getWeight() {
        return 3.62;
    }
}