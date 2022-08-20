package ru.yandex.practicum.filmorate.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.*;


@Service
@Slf4j
@NoArgsConstructor
public class FilmService {
    FilmStorage filmStorage;
    UserStorage userStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage, UserStorage userStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

    public Film createFilm(Film addFilm) {
        return filmStorage.createFilm(addFilm);
    }

    public Film updateFilm(Film updateFilm) {
       return filmStorage.updateFilm(updateFilm);
    }
    public List<Film> getFilms() {
        return filmStorage.getFilms();
    }
    public Film getFilmById(int id){
        return filmStorage.getFilmById(id);
    }
    //добавить like
    public void addLike(int filmId, int userId) {
        filmStorage.addLike(filmId, userId);
    }

    //удалить like
    public void deleteLike(int filmId, int userId) {
        User user = userStorage.getUserById(userId);
        filmStorage.deleteLike(filmId, user);
    }

    //получить список Топ-10 фильмов
    public Set<Film> popularFilms(int count) {
        return filmStorage.popularFilms(count);

    }
}
