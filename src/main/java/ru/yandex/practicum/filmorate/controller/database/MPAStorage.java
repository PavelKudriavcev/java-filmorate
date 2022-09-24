package ru.yandex.practicum.filmorate.controller.database;

import ru.yandex.practicum.filmorate.model.RatingMPA;

import java.util.List;

public interface MPAStorage {

    List<RatingMPA> getAllMpa();

    RatingMPA getMpaById(int id);
}
