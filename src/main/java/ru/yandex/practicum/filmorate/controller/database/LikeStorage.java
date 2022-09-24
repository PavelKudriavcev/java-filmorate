package ru.yandex.practicum.filmorate.controller.database;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Likes;

import java.util.Collection;

public interface LikeStorage {
    Collection<Film> getPopularFilms(int limit);

    void saveLike(Likes likes);

    void deleteLike(Likes likes);
}