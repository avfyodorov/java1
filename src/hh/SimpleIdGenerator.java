package hh;

/**
 * Автор: Фёдоров Александр
 * Дата:  11.12.2023  18:06
 */
public class SimpleIdGenerator {
    private int id = 1;

    public int getCurrentId() {
        return id - 1;
    }

    public int generateId() {
        return id++;
    }

    public void resetIndex() {
        id = 1;
    }
}


