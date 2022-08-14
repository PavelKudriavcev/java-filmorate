package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@Validated
@RequestMapping("/films")
public class FilmController {
    List<Film> films = new ArrayList<>();
    int filmsId = 1;


    void isValidFilm(Film film) {
        if (film.getName().isEmpty()) {
            throw new ValidationException( "название не может быть пустым");
        }
        if ((film.getDescription().length() >= 200) | (film.getDescription().isEmpty())) {
            throw new ValidationException("максимальная длина описания — 200 символов");
        }
        if ((film.getReleaseDate()).isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("дата релиза — не раньше 28 декабря 1895 года");
        }
        if (film.getDuration() <= 1) {
            throw new ValidationException("продолжительность фильма должна быть положительной.");
        }
    }

    @PostMapping
    public Film createFilm(@RequestBody @Valid Film addFilm){
        log.debug("добавление фильма");
        isValidFilm(addFilm);
        addFilm.setId(filmsId++);
        films.add(addFilm);
        return addFilm;
    }

    @PutMapping
    public Film updateFilm(@RequestBody @Valid Film addFilm){
        log.debug("обновление фильма");
        for (Film filmUp : films){
            if(filmUp.getId() == addFilm.getId()){
                films.remove(filmUp);
                films.add(addFilm);
            }else {
                throw new ValidationException("Такого фильма нет");
            }
        }
        return addFilm;
    }

    @GetMapping
    public List<Film> getFilms(){
        log.debug("получение всех фильмов");
        return films;
    }
}
