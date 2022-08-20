package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.controller.NotFoundObjectException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.*;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {
    private Map<Integer, Film> films = new HashMap<>();

    @Override
    public Film createFilm(Film addFilm) {
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
        Set<Film> collect = new HashSet<>();
        List<Film> films = getFilms();
        films.stream()
                .sorted(Comparator.comparingInt(Film::getCountLike).reversed())
                .limit(count)
                .forEach(collect::add);
        return collect;
    }

}
