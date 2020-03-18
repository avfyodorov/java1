package ru.progwards.java1.lessons.maps;

//Реализовать класс, подсчитывающий частоту использования слов и букв в словах на основе текстов.
// Методы:
//
//        2.1 public void processFile(String fileName) - загрузить содержимое файла
//
//        2.2 public Map<Character, Integer> getLetters() - вернуть Map,
//        который содержит все найденные буквы и цифры, и количество раз,
//        которое встретился каждый искомый символ. Знаки препинания, такие как “.,!? @” и
//        др не учитывать.
//        2.3 public Map<String, Integer> getWords() - вернуть Map, который содержит все
//        найденные слова и количество раз, которое каждое слово встретилось. Знаки препинания,
//        такие как “.,!? @” и др являются разделителями.
//
//        2.4 Протестировать на файле wiki.train.tokens (во вложении), для отладки можно
//        использовать wiki.test.tokens

import java.io.File;
import java.util.*;

public class UsageFrequency
{
  List<String> list = new ArrayList<>();

  public void processFile(String fileName) throws Exception
  //- загрузить содержимое файла
  {
    try (Scanner in = new Scanner(new File(fileName)))
    {
      while (in.hasNext())
      {
        String s = in.next();
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray())
          if (Character.isLetterOrDigit(c))
            sb.append(c);

        if (sb.length() > 0)
          list.add(sb.toString());
      }
    }
    catch (Exception e)
    {
      throw new Exception(e.getMessage());
    }
  }

  public Map<Character, Integer> getLetters()
//        который содержит все найденные буквы и цифры, и количество раз,
//        которое встретился каждый искомый символ. Знаки препинания, такие как “.,!? @” и
//        др не учитывать.
  {
    Map<Character, Integer> res = new TreeMap<>();

    for (String word : list)
      for (char c : word.toCharArray())
        if (res.containsKey(c))
          res.put(c, res.get(c) + 1);
        else
          res.put(c, 1);

    return res;
  }

  public Map<String, Integer> getWords()
  //- вернуть Map, который содержит все
//        найденные слова и количество раз, которое каждое слово встретилось. Знаки препинания,
//        такие как “.,!? @” и др являются разделителями.
  {
    Map<String, Integer> res = new TreeMap<>();

    for (String word : list)
      if (res.containsKey(word))
        res.put(word, res.get(word) + 1);
      else
        res.put(word, 1);

    return res;
  }

  public static void main(String[] args) throws Exception
  {
    UsageFrequency usageFrequency = new UsageFrequency();
    //usageFrequency.processFile("qyqy.txt");//"wiki.test.tokens");
    usageFrequency.processFile("wiki.test.tokens");

    System.out.println(usageFrequency.list.toString());
    System.out.println(usageFrequency.getLetters());
    System.out.println(usageFrequency.getWords());
  }
}
