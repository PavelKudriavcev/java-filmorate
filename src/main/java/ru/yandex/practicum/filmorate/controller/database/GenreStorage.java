package ru.yandex.practicum.filmorate.controller.database;

import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;

public interface GenreStorage {

    List<Genre> getAll();

    Genre get(int id);
}