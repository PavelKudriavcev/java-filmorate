package ru.yandex.practicum.filmorate.storage;

import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

public interface FilmStorage {

    Film createFilm(@Valid @RequestBody Film addFilm);


    Film updateFilm(@Valid @RequestBody Film newFilm);

    List<Film> getFilms();

    Film getFilmById(int id);

/*
    List<Film> getAllFilms();

    void addLike(int filmId, int userId);

    void deleteLike(int filmId, User user);

    Set<Film> popularFilms(int count);

    Film update(Film film);

    Film getById(Integer id);

    Film getById(Long id);

    List<Film> getAllFilms();*/
}
