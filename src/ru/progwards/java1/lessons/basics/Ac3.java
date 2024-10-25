package ru.progwards.java1.lessons.basics;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Автор: Фёдоров Александр
 * Дата:  07.08.2024  22:38
 */
public class Ac3 {
    static final double PI = 3.14f;
    static final float PI_F = 3.14f;
    static float r = 6371.2f;

//    public static double volumeBallDouble(double radius) {
//        return (4.0 / 3.0) * PI * Math.pow(r,3);
//    }

    public static float volumeBallFloat(float radius) {
        return (float) ((4.0 / 3.0) * PI * Math.pow(r,3));
    }
    public static Float volumeBallFloatOld(Float radius) {
        return (float) ((4.0f / 3.0f) * PI * Math.pow(radius, 3)); // Возвращение объема
    }
//    static final float PI_F = 3.14f;
    public static float volumeBallFloatMy(float radius)
    //будет возвращать объём шара с радиусом radius и основана на типе float.
    {
        return (float) (4.0 / 3.0 * PI_F * Math.pow(radius, 3.0));
    }

//    public static double calculateAccuracy(double radius) {
//        return volumeBallDouble(r) - volumeBallFloat((float) r);
//    }

    public static void main(String[] args) {

        ArrayList<String> shoppingList = new ArrayList<>(10);
        System.out.println(shoppingList.size());

        System.out.println("Список продуктов:");
        for (int j = 0; j < shoppingList.size(); j++) {
            System.out.println((j + 1) + ". " + shoppingList.get(j));
        }

        shoppingList.clear();


int[] i= new int[30];
int m = Arrays.stream(i).max().orElse(9);
        System.out.println("i="+m);

        //        System.out.println(volumeBallFloatMy((float) r));
//        System.out.println(volumeBallFloatOld((float) r));
//        System.out.println(volumeBallFloat((float) r));
    }
//    public static void main(String[] args) {
//        System.out.println("Объем земли с радиусом 6371.2 км (Double): "
//                + volumeBallDouble(r));
//        System.out.println("Объем земли с радиусом 6371.2 км (Float): "
//                + volumeBallFloat((float) r));
//        System.out.println("Разница между Double и Float: " + calculateAccuracy(r));
//    }
}
/*
1.0827597414810692E12
1.0827599E12
-163798.9307861328

Объем земли с радиусом 6371.2 км (Double): 1.0827597414810692E12
Объем земли с радиусом 6371.2 км (Float): 1.0827598E12
Разница между Double и Float: -32726.930786132812

 */
