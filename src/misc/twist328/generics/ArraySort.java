package misc.twist328.generics;

import java.math.BigDecimal;
import java.util.Arrays;

public class ArraySort {

   /* Реализовать статический метод с именем sort, сортирующий произвольный массив обобщающих типов, по алгоритму из первого урока:


   1. Берем первый элемент и сравниваем его со вторым, если второй меньше, меняем элементы в массиве местами.
   2. Далее, сравниваем первый элемент с третьим, и если третий меньше, меняем их местами.
   3. Так делаем для всех элементов с индексом больше первого
   4. Берем второй элемент и сравниваем его с третьим, если нужно, меняем местам
   5. Далее сравниваем второй элемент с четвертым, и если нужно, меняем местами.
   6. Делаем так для всех элементов, с индексом больше 2-го
   7. Переходим к элементу с индексом 3...
   8. Обобщая, алгоритм звучит следующим образом - сделать 2 вложенных цикла, внешний по i и внутренний по j
    Внутренний цикл начинается от i+1, и если a[i] > a[j], то нужно поменять элементы a[i] и a[j] местами
    */

    public static void main(String[] args) {

        // Double[]array = {2.1,100.35, 8.2, 8.88, 0.2, 3.14, 100.35};
        // Integer[]array={999,6666,775,44,3,35,2,1-78};


        BigDecimal[] array = {BigDecimal.valueOf(35.6), BigDecimal.valueOf(8222.888), BigDecimal.valueOf(92.392),
                BigDecimal.valueOf(0), BigDecimal.valueOf(-4874.88)};

        sort(array);
        System.out.println(Arrays.toString(array));

        String[] as = {"Ярослав", "Ольга", "Анна"};
        sort(as);
        for (String a : as) {
            System.out.print(a + " ");
        }

    }

    public static <T extends Comparable> void sort(T... array) {

        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j].compareTo(array[i]) == 1) {
                    //значение функции ==-1 или <0 отсортирует элементы от большего к меньшему
                    T tmpValue = array[i];
                    array[i] = array[j];
                    array[j] = tmpValue;
                }
            }
        }
    }
}
