package misc.victorio.sets;

/**
 * Автор: Фёдоров Александр
 * Дата:  05.05.2022  12:07
 */
        import java.util.*;

public class ProductAnalytics {
    private static List<Shop> shops;
    private static List<Product> products;

    public ProductAnalytics(List<Shop> shops, List<Product> products) {
        this.shops = shops;
        this.products = products;
    }

    public Set<Product> existInAll() {
        Set<Product> prod = new HashSet<>(shops.get(0).getProducts());
        for (int i = 1; i < shops.size(); i++) {
            prod.retainAll(shops.get(i).getProducts());
        }
        return prod;
    }

    public Set<Product> existAtListInOne() {
        Set<Product> prod = new HashSet<>();
        for (Shop shop : shops) {
            prod.addAll(shop.getProducts());
        }
        return prod;
    }

    public Set<Product> notExistInShops() {
        Set<Product> prod = new HashSet<>(products);
        for (Shop shop : shops) {
            prod.removeAll(shop.getProducts());
        }
        return prod;
    }

    public Set<Product> existOnlyInOne() {//
        Set<Product> tmp = new HashSet<>(existAtListInOne());
        Set<Product> prod = new HashSet<>(existAtListInOne());
        for (Product el : tmp) {
            int count = 0;
            for (Shop shop : shops) {
                if (shop.getProducts().contains(el)) {
                    count++;
                }
                if (count > 1) {
                    prod.remove(el);
                    count = 0;
                }
            }
        }

        return prod;
    }

    public static void main(String[] args) {
        Product a1 = new Product("a");
        Product a2 = new Product("aa");
        Product a3 = new Product("aaa");
        Product a4 = new Product("aaaa");
        Product a5 = new Product("b");
        Product a6 = new Product("bb");
        Product a7 = new Product("bbb");
        Product a8 = new Product("bbbb");
        Product a9 = new Product("c");

        Product n1 = new Product("ooo");
        Product n2 = new Product("ppp");
        Product n3 = new Product("k");

        Shop q1 = new Shop(List.of(a1, a2, a3));
        Shop q2 = new Shop(List.of(a1, a4, a5));
        Shop q3 = new Shop(List.of(a1, a7, a6));
        Shop q4 = new Shop(List.of(a9, a1, a3, a8));

        ProductAnalytics prAn = new ProductAnalytics(List.of(q1, q2, q3, q4),
                List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9, n1, n2, n3));
        Set<Product> result = prAn.notExistInShops();
        for (Product el : result) {
            System.out.println(el);
        }

    }
}

