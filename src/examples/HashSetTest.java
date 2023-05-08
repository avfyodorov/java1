package examples;

import java.util.*;

/**
 * Автор: Фёдоров Александр
 * Дата:  28.02.2023  12:22
 */
public class HashSetTest {
    public static void main(String[] args) {
        TreeMap<Integer,Film> set = new TreeMap<>();
        set.put(1, new Film(1,"qyqy"));
String s="1".repeat(200);
/*
Stata st=Stata.valueOf("A");
        System.out.println(st);
st=Stata.valueOf("M");
        System.out.println(st);
*/
        List<Film> films = new ArrayList<>();
films.add(new Film(1,"qyqy999"));
        films.stream()
                .map(Film::toString)
                .forEach(System.out::println);
Map<Integer,Film> mapa=new HashMap<>();
List<Film> flm=new ArrayList<>(mapa.values());
    }
}

enum Stata {A,B,C

}

class Film{
    int id;

    public Film(int id, String name) {
        this.id = id;
        this.name = name;
    }

    String name;

}
