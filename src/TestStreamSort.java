import java.util.*;
import java.util.stream.Collectors;

/**
 * Автор: Фёдоров Александр
 * Дата:  21.04.2023  21:59
 */
public class TestStreamSort {
    public static void main(String[] args) {
        HashMap<Long, Film> films = new HashMap<>();
        films.put(1L, new Film(1L, 25));
        films.put(2L, new Film(2L, 12));
        films.put(3L, new Film(3L, 35));
        films.put(4L, new Film(4L, 21));
        films.put(5L, new Film(5L, 17));

        for (int i = 1; i <= films.size(); i++) {
            System.out.println(films.get((long) i));
        }

        System.out.println("=====================");

        Comparator<Film> filmLikesComparator = Comparator.comparingInt(film -> film.getLikes().size());

        List<Film> mostLikes = films.values()
                .stream()
                .sorted(filmLikesComparator.reversed()).limit(3)
                .collect(Collectors.toList());

        mostLikes.forEach(System.out::println);
        //===============
        String s = "  ";
        System.out.println(s.isBlank() ? "Пустая строка" : "Строка состоит из пробелов");

        String str = null;
        if (str == null || str.isBlank())
            System.out.println("Проверка прошла успешно");
    }
}

class Film {
    Long id;
    Set<Long> likes = new HashSet<>();

    Film(Long id, int countLikes) {
        this.id = id;
        for (int i = 0; i < countLikes; i++) {
            likes.add((long) i);
        }
    }

    public Set<Long> getLikes() {
        return likes;
    }

    @Override
    public String toString() {
        return "Film{id=" + id + " likes== " + likes.size() + '}';
    }
}