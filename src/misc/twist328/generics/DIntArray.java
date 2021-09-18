package misc.twist328.generics;

import java.util.ArrayList;

public class DIntArray { //Завел сюда для теста скорости работы массивов(и вообще работает ли с дженериком понять)
    int[] array = new int[1000];

    public void add(int item) {
        int[] newArray = new int[array.length+1];
        copyData(array, newArray);
        newArray[array.length] = item;
        array = newArray;
    }

    void copyData(int[] src, int[] dst) {
        for(int i=0; i<src.length; i++)
            dst[i] = src[i];
    }

    public int get(int index) {
        return array[index];
    }

    public int size() {
        return array.length;
    }
}

