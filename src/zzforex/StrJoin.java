package zzforex;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Автор: Фёдоров Александр
 * Дата:  31.07.2024  20:43
 */
public class StrJoin {
    public static void main(String[] args) {
        List<String> uris = new ArrayList<>();
        uris.add("aaa");
        uris.add("bbb");
        uris.add("ссс");

        String urisString = String.join(",", uris);

        System.out.println(urisString);

        LocalDateTime start = LocalDateTime.now();
        boolean unique = true;

        Map<String, Object> parameters = Map.of(
                "start", start,
                "urisString", urisString,
                "unique", unique,
                "uris", uris
        );

        System.out.println(parameters);
    }
}
