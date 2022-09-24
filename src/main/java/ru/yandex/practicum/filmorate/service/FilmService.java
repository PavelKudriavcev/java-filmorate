package ru.yandex.practicum.filmorate.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.controller.database.FilmDbStorage;
import ru.yandex.practicum.filmorate.controller.database.LikeDbStorage;
import ru.yandex.practicum.filmorate.controller.database.LikeStorage;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Likes;

import java.util.*;


@Service
@Slf4j
public class FilmService {
    private final FilmDbStorage filmStorage;
    private final UserService userService;
    private final LikeStorage likeStorage;

    @Autowired
    FilmService(FilmDbStorage FilmDbStorage, LikeDbStorage databaseLikeStorage,
                UserService userService) {
        this.filmStorage = FilmDbStorage;
        this.userService = userService;
        this.likeStorage = databaseLikeStorage;
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

    public Film getFilmById(int id) {
        return filmStorage.getFilmById(id);
    }

    //добавить like
    public void addLike(int filmId, int userId) {

        likeStorage.saveLike(Likes
                .builder()
                .film(filmStorage.getFilmById(filmId))
                .user(userService.getUserById(userId))
                .build());
    }

    //удалить like
    public void deleteLike(int filmId, int userId) {
        likeStorage.deleteLike(Likes
                .builder()
                .film(filmStorage.getFilmById(filmId))
                .user(userService.getUserById(userId))
                .build());
    }

    //получить список Топ-10 фильмов
    public Set<Film> popularFilms(Integer count) {
        return (Set<Film>) likeStorage.getPopularFilms(count != null ? count : 10);

    }


}