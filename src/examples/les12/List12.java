package examples.les12;

//Напишите метод, с сигнатурой public List<Integer> filter(List<Integer> list)
// который работает по следующему алгоритму
//
//        суммирует значения всех элементов списка
//        удаляет из списка элементы, значение которых меньше суммы, деленной на 100 (целочисленное деление)
//        возвращает результирующий список


import java.util.ArrayList;
import java.util.List;

public class List12
{
  public List<Integer> filter(List<Integer> list)
  {
//суммирует значения всех элементов списка
    int sum = 0;
    for (Integer item : list)
      sum = sum + item;
    sum = sum / 100;

//удаляет из списка элементы, значение которых меньше суммы, деленной на 100 (целочисленное деление)
    for (int i = list.size() - 1; i >= 0; i--)
      if (list.get(i) >= sum)
        list.remove(i);

    return list;
  }

  public static void main(String[] args)
  {
    List<Integer> list = new ArrayList();
    for (int i = 0; i < 50; i++)
      list.add(i);
    System.out.println(list.toString());

    List12 o = new List12();
    o.filter(list);

    System.out.println(list.toString());
  }
}
/*
public List<Integer> filter(List<Integer> list) {
        List listrez = new ArrayList();
        long res = 0;
        for (int i = 0; i < list.size(); i++) {
            res += list.get(i); // Подсчитали сумму элементов коллекции
        }
        long res2 = res / 100; // целочисленно разделили на сто
        for (int num : list) {
            if (num < res2) {
                listrez.add(num); // добавили в новую коллекцию элементы, меньшие, чем сотая от суммы эл-тов
            }
        }
        return listrez; // Возвращаем список меньших, тогда как задача гласит их убрать и вернуть исходную коллекцию без них
    }

 */