package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.controller.Exceptions.NotFoundObjectException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;
import java.util.stream.Collectors;


@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {
    private Map<Integer, Film> films = new HashMap<>();
    private int filmsId = 1;

    @Override
    public Film createFilm(Film addFilm) {
        addFilm.setId(filmsId);
        filmsId++;
        films.put(addFilm.getId(), addFilm);
        return addFilm;
    }

    @Override
    public Film updateFilm(Film newFilm) {
        if (!films.containsKey(newFilm.getId())) {
            throw new NotFoundObjectException("Такого фильма нет");
        } else {
            films.put(newFilm.getId(), newFilm);
            return newFilm;
        }
    }

    @Override
    public List<Film> getFilms() {
        return new ArrayList<>(films.values());
    }

    @Override
    public Film getFilmById(int id) {
        if (!films.containsKey(id)) {
            throw new NotFoundObjectException("Такого фильма нет");
        } else {
            return films.get(id);
        }
    }

    public void addLike(int filmId, int userId) {
        Film film = getFilmById(filmId);
        film.getLikes().add(userId);
        updateFilm(film);
    }

    public void deleteLike(int filmId, User user) {
        Film film = getFilmById(filmId);
        film.getLikes().remove(user.getId());
        updateFilm(film);
    }

    public Set<Film> popularFilms(int count) {
        List<Film> films = getFilms();
        return films.stream()
                .sorted(Comparator.comparingInt(Film::getCountLike).reversed())
                .limit(count)
                .collect(Collectors.toSet());
    }
}