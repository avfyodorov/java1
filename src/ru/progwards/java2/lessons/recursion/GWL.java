package ru.progwards.java2.lessons.recursion;

        import java.time.Instant;

        import java.util.ArrayList;
        import java.util.Comparator;
        import java.util.List;
        import java.util.stream.Collectors;

public class GWL {
   private List<Goods> goodsList = new ArrayList<>();

   public void setGoods(List<Goods> list) {
      goodsList.addAll(list);
      goodsList.forEach(System.out::println);
   }

   public List<Goods> sortByName() {
      goodsList.sort(Comparator.comparing(n -> n.name));
      goodsList.forEach(System.out::println);
      return goodsList;
   }

   public List<Goods> sortByNumber() {
      goodsList.sort(Comparator.comparing(n -> n.number.toLowerCase()));
      goodsList.forEach(System.out::println);
      return goodsList;
   }

   public List<Goods> sortByPartNumber() {
      goodsList.sort(Comparator.comparing(n -> n.number.substring(0, 2).toLowerCase()));
      goodsList.forEach(System.out::println);
      return goodsList;
   }

   public List<Goods> sortByAvailabilityAndNumber() {
      List<Goods> newList = goodsList.stream().sorted(Comparator.comparing(s -> s.number.toLowerCase()))
              .sorted(Comparator.comparing(s -> s.available)).collect(Collectors.toList());
      newList.forEach(System.out::println);
      return newList;
   }

   public List<Goods> expiredAfter(Instant date) {
      List<Goods> newList = goodsList.stream().filter(s -> s.expired.compareTo(date) > 0)
              .sorted(Comparator.comparing(d -> d.expired)).collect(Collectors.toList());
      newList.forEach(System.out::println);
      return newList;
   }

   public List<Goods> сountLess(int count) {
      List<Goods> newList = goodsList.stream().filter(s -> s.available < count).collect(Collectors.toList());
      newList.forEach(System.out::println);
      return newList;
   }

   public List<Goods> сountBetween(int count1, int count2) {
      List<Goods> newList = goodsList.stream().filter(s -> s.available > count1 && s.available < count2).collect(Collectors.toList());
      newList.forEach(System.out::println);
      return newList;
   }
}

