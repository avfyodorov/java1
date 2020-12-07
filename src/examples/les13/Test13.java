package examples.les13;

import java.util.*;

import static java.util.Arrays.*;

public class Test13
{
  public static void iSetTest()
  {
    Set<Integer> iSet = new HashSet<>();
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
      {
        iSet.add(i + j);
      }
    System.out.println(iSet.size());
  }

  public static void wSetTest()
  {
    String TEXT = "на дворе трава на траве дрова не руби дрова на траве двора";
    Set<String> wordSet = new HashSet<>(asList(TEXT.split(" ")));

    Iterator<String> iter = wordSet.iterator();
    while (iter.hasNext())
      if (iter.next().contains("ра"))
        iter.remove();

    System.out.println(wordSet.size());
  }

  public static void cset()
  {
    Set<Integer> fiboSet1000 =
            Set.of(0, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987);
    int sum = 0;
    for (int i = 2; i < 10; i++)
      sum += fiboSet1000.contains(i) ? 1 : 0;
    System.out.println(sum);
  }

  //  Реализуйте метод,с сигнатурой public Set<Integer> a2set(int[] a),
//  который преобразует массив в множество
  public Set<Integer> a2set(int[] a)
  {
    Set<Integer> res =  new HashSet<Integer>();
    for (int i = 0; i < a.length; i++) res.add(a[i]);
    return res;
  }


//  Укажите, что будет выведено на консоль в результате выполнения метода
  //      8
  public void unionOfSets(final Set set1, final Set set2) {
    HashSet result = new HashSet(set1);
    result.addAll(set2);
    System.out.println(result.size());
  }

//  set1 = [1, 3, 5, 12, 45]
//  set2 = [12, 44, 2, 1, 4]


  public static void main(String[] args)
  {
    // output == 5
    iSetTest();

    //output == 5
    wSetTest();

    // output == 4
    cset();

    int[] a = {5, 8, 7, 7};
    Test13 test13 = new Test13();
    System.out.println(test13.a2set(a));

    Set<Integer> s1=Set.of(1, 3, 5, 12, 45);
    Set<Integer> s2=Set.of(12, 44, 2, 1, 4);
    test13.unionOfSets(s1, s2);

    System.out.println(addAsStrings(1,3));


    int[] i = {5, 8, 7, 7};
//    ArrayList<Integer>z=Arrays.asList(i);
    System.out.println(Arrays.toString(i));

  }

  static int addAsStrings(int n1, int n2)
  {
    return Integer.parseInt( ""+n1+n2);
  }
static void p(int i){
  double d=1.0;
  float f=(float) d;
  }
}
