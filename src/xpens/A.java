package xpens;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Автор: Фёдоров Александр
 * Дата:  24.11.2024  16:25
 */
@Setter
@Getter
public class A {
    private String name;
    private int id;

    public A(String name, int id) {
        this.name = name;
        this.id = id;
    }
    Map<Integer,Long> map=new HashMap<>();

    public static void main(String[] args) {
        A a = new A("f", 8);
        a.map.merge(1, 99L, Long::sum);
    }
}

class B extends A{
    public B(String name, int id) {
        super(name, id);
    }
}
