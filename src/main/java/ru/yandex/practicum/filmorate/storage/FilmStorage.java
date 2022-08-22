package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Set;

public interface FilmStorage {

    Film createFilm(Film addFilm);


    Film updateFilm(Film newFilm);

    List<Film> getFilms();

    Film getFilmById(int id);

    void addLike(int filmId, int userId);

    void deleteLike(int filmId, User user);

    Set<Film> popularFilms(int count);
}
