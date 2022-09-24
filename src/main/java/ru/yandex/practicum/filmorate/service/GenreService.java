package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.controller.database.GenreDbStorage;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class GenreService {
    private final GenreDbStorage genreStorage;

    public GenreService(GenreDbStorage genreStorage) {
        this.genreStorage = genreStorage;
    }

    public Collection<Genre> getAll() {
        return genreStorage.getAll();
    }

    public Genre get(int id) {
        Genre genre = genreStorage.get(id);
        if (genre == null) throw new NoSuchElementException();
        return genre;
    }
}
