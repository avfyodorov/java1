package examples;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class T15
{
  HashMap<Integer, String> a2map(int[] akey, String[] aval)
  {
    HashMap<Integer, String> res = new HashMap<>();
    for (int i = 0; i < akey.length; i++)
    {
      res.put(akey[i], aval[i]);
    }
    return res;
  }
//  , который создает Map на основе пары массивов akey (ключи) и aval (значения).
//  Первому элементу массива akey соответствует первый элемент массива aval, и т.д.
//  Размеры массивов одинаковые.

  public static void main(String[] args)
  {
    T15 t15 = new T15();
    int[] akey = {1, 3};
    String[] aval = {"111111", "3333"};
    System.out.println(t15.a2map(akey, aval));
//-------------------------

    Map<Integer, String> map1 = new HashMap<>();
    map1.put(3, "qyqy");
    System.out.println("added - " + t15.fillHoles(map1, 3));
//-------------------------

    TreeMap<Integer, String> map = new TreeMap<>();
    map.put(1, "Один");
    map.put(9, "Девять");
    t15.checkAndAdd(map, 0, "Zero");
    t15.checkAndAdd(map, 3, "Три");
    t15.checkAndAdd(map, 2, "Два");
    t15.checkAndAdd(map, 3, "Three");
    t15.checkAndAdd(map, 10, "Ten");
    System.out.println(map);
  }

  void checkAndAdd(TreeMap<Integer, String> map, Integer key, String value)
//  , который добавляет пару key-value в map при выполнении следующих условий:

//  значение с таким key должно отсутствовать
//  значение key долно быть больше головы TreeMap
//  значение key долно быть меньше хвоста TreeMap
  {
    if (!map.containsKey(key) &&
            map.lowerKey(key) != null &&  map.containsKey(map.lowerKey(key)) &&
            map.higherKey(key) !=   null && map.containsKey(map.higherKey(key))
    )
      map.put(key, value);
  }


  //  Создайте метод с сигнатурой
  int fillHoles(Map<Integer, String> map, int size)
//  который вставляет в HashMap пару <n> "Число n", если она там отсутствует,
//  Проверить от 1 до максимального числа size включительно.
//  Метод возвращает количество добавленных элементов. Пример пары: 1 "Число 1"
  {
    int res = 0;
    for (int i = 1; i <= size; i++)
    {
      if (map.putIfAbsent(i, "Число " + i) == null)
        res++;
    }
    return res;
  }

}
