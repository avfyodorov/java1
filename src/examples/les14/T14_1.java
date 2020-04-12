package examples.les14;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class T14_1
{
  static void dequeueTest()
  {
    ArrayDeque deque = new ArrayDeque<>();

    for (int i = 0; i <= 10; i++)
    {
      deque.offer(i);
      if (i % 2 == 0)
        deque.poll();
    }

    System.out.println(deque);
  }

  //output  ==  [1, 3, 9, 10]
  static void pqTest()
  {
    PriorityQueue pQueue = new PriorityQueue<>();
    pQueue.offer(10);
    pQueue.offer(1);
    pQueue.offer(9);
    pQueue.offer(3);
    System.out.println(pQueue);
  }

  ArrayDeque<Integer> array2queue(int[] a)
  {
    ArrayDeque<Integer> res = new ArrayDeque<>();
    for (int i = 0; i < a.length; i++)
    {
      res.offer(a[i]);
    }
    return res;
  }

  public static void main(String[] args)
  {
// output  == [6, 7, 8, 9, 10]
    dequeueTest();

    pqTest();

    T14_1 main_obj = new T14_1();
    int[] a = {8, 5, 3, 1};
    System.out.println(main_obj.array2queue(a));

//==================================================
    ArrayDeque<Integer> deque=new ArrayDeque<>();
    deque.offer(4);
    deque.offer(8);

    System.out.println("first+last: "+main_obj.sumLastAndFirst(deque));
  }

//  Реализуйте метод с сигнатурой
 int sumLastAndFirst(ArrayDeque<Integer> deque)
  //который возвращает сумму первого и последнего элемента очереди. При пустой очереди вернуть 0
 {
   if (deque.isEmpty())
     return 0;
   else
     return deque.peekFirst()+deque.peekLast();
 }

}
