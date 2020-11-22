package hh;

import java.util.Arrays;

public class MegreSort {

    public void sort(int[] arr, int low, int high) {
        if (low >= high)
            return;
        int mid = low + (high - low) / 2;
        sort(arr, low, mid);
        sort(arr, mid + 1, high);
        Megre(arr, low, mid, high);
    }

    public void Megre(int[] arr, int low, int mid, int high) {
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++)
            temp[k] = arr[k];

//левая закончилась
//правая закончилась
//левый > правого
//левый <= правого
        for (int k = low; k <= high; k++) {
            if (i > mid)
                arr[k] = temp[j++];
            else if (j > high)
                arr[k] = temp[i];
            else if (temp[j] < temp[i])
                arr[k] = temp[j++];
            else
                arr[k] = temp[i++];
        }

    }

    int[] temp;

    public static void main(String[] args) {
        int[] arr = {5, 2, 4, 6, 1, 3, 2, 6};

        MegreSort mane = new MegreSort();
        mane.temp = new int[arr.length];

        System.out.println("Before :  " + Arrays.toString(arr));
        mane.sort(arr, 0, arr.length - 1);
        System.out.println("After  :  " + Arrays.toString(arr));
    }
}
