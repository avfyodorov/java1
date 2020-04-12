package examples.les13;

import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

class User
{
  public Integer id;
  public String name;

  User(Integer id, String name)
  {
    this.id = id;
    this.name = name;
  }
}

//  Создайте метод с сигнатурой public TreeSet<User> createSet()
//  который создает и возвращает TreeSet так, что бы пользователи оказались упорядочены по убыванию id


public class TreeCompare
{
  public TreeSet<User> createSet()
  {
    TreeSet<User> res = new TreeSet<>(new Comparator<User>()
    {
      @Override
      public int compare(User o1, User o2)
      {
        return Integer.compare(o2.id, o1.id);
      }
    });

    return res;
  }

  public static void main(String[] args)
  {
    TreeCompare comp = new TreeCompare();
    TreeSet<User> users = comp.createSet();
    users.add(new User(1, "111"));
    users.add(new User(3, "333"));
    users.add(new User(5, "555"));
    users.add(new User(8, "888"));
    for (User u : users)
      System.out.println("index: " + u.id + " name:" + u.name);

  }
}
