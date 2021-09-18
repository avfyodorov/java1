package misc.vg.generics;

public class ArraySort {
    public static <T extends Comparable> void sort(T[] a) {
        for (int i=0; i<a.length; i++) {
            for (int j=i+1; j<a.length; j++) {
                if (a[i].compareTo(a[j])>0) {
                    T a1 = a[i];
                    a[i] = a[j];
                    a[j] = a1;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] ai = {5,2,8,7};
        sort(ai);
        for (Integer i : ai) {
            System.out.print(i + " ");
        }
        String[] as = {"Ярослав", "Ольга", "Анна"};
        sort(as);
        for (String a : as) {
            System.out.print(a + " ");
        }
    }
}
