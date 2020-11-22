package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

/*
Сделать итератор MatrixIterator по двумерному массиву (матрице),
который разворачивает матрицу в линейную последовательность построчно:
a[0][0], a[0][1], ...a[0][N],a[1][0], a[1][1]...a[1][N]... a[M][N]

Шаблон для итератора взять от ArrayIterator
 */
public class MatrixIterator2<T> implements Iterator<T> {

    private T[][] array;
    private int currentIndex;
    private int currentIndexRow;
    private int currentIndexCol;
    private int currentSize;
    private int currentSizeCol;

    MatrixIterator2(T[][] array) {
        System.out.println("Конструктор запущен");
        this.array = array;
        this.currentIndex = 0;
        this.currentIndexRow = 0;
        this.currentIndexCol = 0;
        this.currentSize = 0;
//не надо        this.currentSizeCol = this.array[0].length;
        for (int i = 0; i < array.length; i++) {
            this.currentSize += array[i].length;
        }
        System.out.println("Размер одномерного массива: " + this.currentSize);
    }

    @Override
    public boolean hasNext() {
        if (this.currentIndex < this.currentSize && this.array[currentIndexRow][currentIndexCol] != null)
            return true;
        else
            return false;
    }

    @Override
    public T next() throws IndexOutOfBoundsException {
        T result = this.array[this.currentIndexRow][this.currentIndexCol];
        this.currentIndex++;
        this.currentIndexCol++;
        if (this.currentIndexCol == this.array[this.currentIndexRow].length) {
            this.currentIndexRow++;
            this.currentIndexCol = 0;
        }
        return result;
    }

    public static void main(String[] args) {
        int volumeArr = 10;
        Integer[][] arrInt = new Integer[4][4];
        arrInt[0] = new Integer[]{1, 2, 3, 4};
        arrInt[1] = new Integer[]{5, 6, 7, 8};
        arrInt[2] = new Integer[]{9, 10, 11, 12};
        arrInt[3] = new Integer[]{13, 14, 15, 16};

        for (Integer[] val1 : arrInt) {
            for (Integer val2 : val1) {
                System.out.println(val2);
            }
        }
        System.out.println("==");
        MatrixIterator2<Integer> arrIterator = new MatrixIterator2<>(arrInt);
        while (arrIterator.hasNext()) {
            System.out.println(arrIterator.next());
        }
    }
}