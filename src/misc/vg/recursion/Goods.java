package misc.vg.recursion;

import java.time.Instant;

public class Goods {
    private final String name; // наименование
    private final String number; // артикул
    private final int available; // количество на складе
    private final double price; // цена
    private final Instant expired; // срок годности

    Goods(String name, String number, int available, double price, Instant expired) {
        this.name = name;
        this.number = number;
        this.available = available;
        this.price = price;
        this.expired = expired;
    }

    String getName() {
        return name;
    }

    String getNumber() {
        return number;
    }

    int getAvailable() {
        return available;
    }

    double getPrice() {
        return price;
    }

    Instant getExpired() {
        return expired;
    }
}
