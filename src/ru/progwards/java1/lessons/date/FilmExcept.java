package ru.progwards.java1.lessons.date;

import java.util.Optional;

/**
 * Автор: Фёдоров Александр
 * Дата:  30.05.2023  21:59
 */
public class FilmExcept {

    public Optional<Film> getFromRepo(int id) {
        //Это вместо запроса.
        Film film = id == 1 ? new Film(id, "111111111111") : null;
        //Что нашли, то и возвращаем в сервис.
        return Optional.ofNullable(film);
    }

    public Film getFromService(int id){
        //Вот проверка на нулл, зачем ещё что-то ловить?
        return getFromRepo(id).orElseThrow(() -> new UnknownFilmException("Фильм не найден: " + id));
    }

    public static void main(String[] args) {
        FilmExcept fe = new FilmExcept();
        System.out.println(fe.getFromService(1));
        //А это аналог фронта. Всё равно здесь придётся что-то анализировать, но это уже другая история. )
        try {
            System.out.println(fe.getFromService(2));
        } catch (UnknownFilmException e){
            System.out.println("Поймали и обработали");
        }
        System.out.println(fe.getFromService(1));
    }
}

class Film {
    int id;
    String name;

    public Film(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

 class UnknownFilmException extends RuntimeException {
    public UnknownFilmException(String message) {
        super(message);
    }

}