package ru.yandex.practicum.filmorate.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.controller.Exceptions.UserDoesNotExistByIdException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;


import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@Validated
@RestController
@Data
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping("/films")
    public Film createFilm(@RequestBody @Valid Film addFilm) {
        filmService.createFilm(addFilm);
        return addFilm;
    }

    @PutMapping("/films")
    public ResponseEntity<Film> updateFilm(@RequestBody @Valid Film newFilm) {
        return newFilm.getId() < 1 ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(filmService.updateFilm(newFilm), HttpStatus.OK);
    }

    @GetMapping("/films")
    public List<Film> getFilms() {
        return filmService.getFilms();
    }

    @GetMapping("/films/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable int id) {
        return id< 1 ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(filmService.getFilmById(id), HttpStatus.OK);
    }

    @PutMapping("/films/{id}/like/{userId}")
    public void addLike(@PathVariable int id, @PathVariable int userId) {
        filmService.addLike(id, userId);
        //return service.getById(id);
    }

    //удалить like от пользователя по id
    @DeleteMapping("/films/{id}/like/{userId}")
    public void deleteLike(@PathVariable int id, @PathVariable int userId) throws UserDoesNotExistByIdException {
        filmService.deleteLike(id, userId);
    }

    //получить список из 10-Топ фильмов
    @GetMapping("/films/popular")
    public Set<Film> popularFilms(@RequestParam(defaultValue = "10") @Positive int count) {
        return filmService.popularFilms(count);
    }
}

