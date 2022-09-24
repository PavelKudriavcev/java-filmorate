package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.controller.database.MPADbStorage;
import ru.yandex.practicum.filmorate.model.RatingMPA;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class MPAService {
    private final MPADbStorage storage;

    public MPAService(MPADbStorage storage) {
        this.storage = storage;
    }

    public List<RatingMPA> getAllMpa() {

        return storage.getAllMpa();
    }

    public RatingMPA getMpaById(int id) {
        RatingMPA ratingMPA = storage.getMpaById(id);
        if (ratingMPA == null) throw new NoSuchElementException();
        return ratingMPA;
    }
}

