package misc.victorio.sets;

/**
 * Автор: Фёдоров Александр
 * Дата:  05.05.2022  12:12
 */
public class Product {
    private String code;

    public Product(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}