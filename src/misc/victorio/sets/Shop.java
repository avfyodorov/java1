package misc.victorio.sets;

import java.util.List;

/**
 * Автор: Фёдоров Александр
 * Дата:  05.05.2022  12:13
 */
public class Shop {

    private List<Product> products;

    public Shop(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "products=" + products +
                '}';
    }

    public List<Product> getProducts() {
        return products;
    }
}