package ru.progwards.java1.lessons.arrays;

/**
 * Автор: Фёдоров Александр
 * Дата:  17.12.2022  11:57
 */
public class Matrix {
    private final int[][] arr;

    public Matrix(int[][] arr) {
        this.arr = arr;
    }

    /*
    не пройден. Метод, вызванный с параметром = 0 возвращает неверное значение.
    Объект создан при помощи массива:
-8   5  -5  -6   7
 2   8   3  -6  -5
-6   3   8   5  -8
 9  -2   8   7   8
-1   9   0  -6   7
Возвращенo: -5. Ожидалось: 7.
     */
    public int maxInRow(int row) {
        
        int res = Integer.MIN_VALUE;
        for (int i : arr[row]) {
            if (i > res) {
                res = i;
            }
        }
        return res;
    }

    /*
Метод, вызванный с параметром = 1 возвращает неверное значение. Объект создан при помощи массива:
 3  -1   7
 5  -9   2
-3   1  -2
Возвращенo: -1. Ожидалось: 1.
     */
    public int maxInCol(int col) {
        int res = Integer.MIN_VALUE;
        for (int[] row : arr) {
            if (row.length > col && row[col] > res) {
                res = row[col];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] a = {-8, 5, -5, -6, 7};
        int[] b = {6, 7, 8, 9, 10, 11, 12};
        int[] c = {4, 5};
        int[] d = {3, -1, 7};
        int[] e = {5, -9, 2};
        int[] f = {-3, 1, -2};
        int[][] matr = {a, b, c};
        int[][] matr1 = {d, e, f};
        Matrix matr2 = new Matrix(matr);
        Matrix matr3 = new Matrix(matr1);
//
        System.out.println("Макс в 1-й строке: " + matr2.maxInRow(0));
        System.out.println("Макс в 2-м столбце: " + matr3.maxInCol(1));
    }
}
