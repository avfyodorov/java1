package examples;

import java.util.List;

/**
 * Автор: Фёдоров Александр
 * Дата:  05.05.2022  21:43
 */
public class Listof {
    public static void main(String[] args) {
        List<A9> list = List.of(new A9(), new A9(), new A8());
        System.out.println(list);
    }
}

class A9{

    @Override
    public String toString() {
        return "A9{}";
    }
}
class A8 extends A9{
    @Override
    public String toString() {
        return "A8{}";
    }
}
