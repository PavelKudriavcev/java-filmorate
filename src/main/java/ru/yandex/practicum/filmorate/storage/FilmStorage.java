package ru.yandex.practicum.filmorate.storage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface FilmStorage {

    Film createFilm(@RequestBody @Valid Film addFilm);


    Film updateFilm(@RequestBody @Valid Film newFilm);

    List<Film> getFilms();

    Film getFilmById(int id);

    void addLike(int filmId, int userId);
    void deleteLike(int filmId, User user);
    public Set<Film> popularFilms(int count);
}
