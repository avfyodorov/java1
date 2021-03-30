package misc.vg.recursion;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GoodsWithLambda {
    private final List<Goods> list = new ArrayList<>();

    public void setGoods(List<Goods> list) {
        this.list.addAll(list);
    }

    public List<Goods> sortByName() {
        return list.stream().
                sorted(Comparator.comparing(Goods::getName)).
                collect(Collectors.toList());
    }

    public List<Goods> sortByNumber() {
        return list.stream().
                sorted((x,y) -> x.getNumber().compareToIgnoreCase(y.getNumber())).
                collect(Collectors.toList());
    }

    public List<Goods> sortByPartNumber() {
        return list.stream().
                sorted((x,y) -> x.getNumber().substring(0, 3).compareToIgnoreCase(y.getNumber().substring(0, 3))).
                collect(Collectors.toList());
    }

    public List<Goods> sortByAvailabilityAndNumber() {
        Comparator<Goods> comparator = Comparator.comparing(Goods::getAvailable);
        comparator = comparator.thenComparing((x,y) -> x.getNumber().compareToIgnoreCase(y.getNumber()));
        return list.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<Goods> expiredAfter(Instant date) {
        Comparator<Goods> comparator = Comparator.comparing(Goods::getExpired);
        return list.stream().sorted(comparator).filter(x -> x.getExpired().isBefore(date)).collect(Collectors.toList());
    }

    public List<Goods> countLess(int count) {
        Comparator<Goods> comparator = Comparator.comparing(Goods::getAvailable);
        return list.stream().sorted(comparator).filter(x -> x.getAvailable() < count).collect(Collectors.toList());
    }

    public List<Goods> countBetween(int count1, int count2) {
        Comparator<Goods> comparator = Comparator.comparing(Goods::getAvailable);
        return list.stream().sorted(comparator).filter(x -> (x.getAvailable() > count1) && (x.getAvailable() < count2)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Goods> lg = new ArrayList<>();
        lg.add(new Goods("caramel", "vv67ty", 3, 0.1, Instant.now()));
        lg.add(new Goods("apple", "av17ty", 2, 0.1, Instant.now()));
        lg.add(new Goods("banana", "aV37ty", 2, 0.1, Instant.parse("2021-01-29T14:50:17.986308500Z")));
        GoodsWithLambda gwl = new GoodsWithLambda();
        gwl.setGoods(lg);
        gwl.sortByName().forEach(x -> System.out.println(x.getName()));
        gwl.sortByNumber().forEach(x -> System.out.println(x.getNumber()));
        gwl.sortByPartNumber().forEach(x -> System.out.println(x.getNumber()));
        gwl.sortByAvailabilityAndNumber().forEach(x -> System.out.println(x.getNumber() + " " + x.getAvailable()));
        gwl.expiredAfter(Instant.now()).forEach(x -> System.out.println(x.getExpired()));
        gwl.countLess(3).forEach(x -> System.out.println(x.getAvailable()));
        gwl.countBetween(2, 3).forEach(x -> System.out.println(x.getAvailable()));
    }
}
