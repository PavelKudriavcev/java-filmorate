package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.service.UserService;


import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.*;

@RestController
@Slf4j
@Validated
@RequestMapping("/films")
public class FilmController {
    private final LocalDate FILM_RELIES = LocalDate.of(1895, 12, 28);

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    void isValidFilm(Film film) {
        if (film.getReleaseDate().isBefore(FILM_RELIES)) {
            throw new ValidationException("дата релиза — не раньше 28 декабря 1895 года");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Film createFilm(@RequestBody @Valid Film addFilm) {
        log.debug("добавление фильма");
        isValidFilm(addFilm);
        return filmService.createFilm(addFilm);
    }

    @PutMapping
    public Film updateFilm(@RequestBody @Valid Film newFilm) {
        log.debug("обновление фильма");
        isValidFilm(newFilm);
        return filmService.updateFilm(newFilm);
    }

    @GetMapping
    public List<Film> getFilms() {
        log.debug("получение всех фильмов");
        return filmService.getFilms();
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable int id) {
        log.debug("получение фильма по ID");
        return filmService.getFilmById(id);
    }
    //поставить like от пользователя по id


    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable int id, @PathVariable int userId) {
        log.debug("пользователь ставит лайк фильму");
        filmService.addLike(id, userId);
    }

    //удалить like от пользователя по id
    @DeleteMapping("/{id}/like/{userId}")
    public void deleteLike(@PathVariable int id, @PathVariable int userId) {
        log.debug("пользователь удаляет лайк");
        filmService.deleteLike(id, userId);
    }

    //получить список из 10-Топ фильмов
    @GetMapping("/popular")
    public Set<Film> popularFilms(@RequestParam(defaultValue = "10")  @Positive int count) {
        log.debug("возвращает список из первых count фильмов по количеству лайков");
        return filmService.popularFilms(count);
    }
}

