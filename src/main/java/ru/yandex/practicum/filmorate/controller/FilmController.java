package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;


import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Validated
@RequestMapping("/films")
public class FilmController {

    private Map<Integer, Film> films = new HashMap<>();
    private int filmsId = 0;
    private final LocalDate FILM_RELIES = LocalDate.of(1895, 12, 28);

    void isValidFilm(Film film) {
        if (film.getReleaseDate().isBefore(FILM_RELIES)) {
            throw new ValidationException("дата релиза — не раньше 28 декабря 1895 года");
        }
    }

    @PostMapping
    public Film createFilm(@RequestBody @Valid Film addFilm) {
        log.debug("добавление фильма");
        isValidFilm(addFilm);
        filmsId++;
        addFilm.setId(filmsId);
        films.put(addFilm.getId(), addFilm);
        return addFilm;
    }

    @PutMapping
    public Film updateFilm(@RequestBody @Valid Film newFilm) {
        log.debug("обновление фильма");
        isValidFilm(newFilm);
        if (!films.containsKey(newFilm.getId())) {
            throw new ValidationException("Такого фильма нет");
        } else {
            films.put(newFilm.getId(), newFilm);
            return newFilm;
        }
    }

    @GetMapping
    public List<Film> getFilms() {
        log.debug("получение всех фильмов");
        return new ArrayList<>(films.values());
    }
}

