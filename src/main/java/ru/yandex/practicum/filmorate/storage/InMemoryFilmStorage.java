package ru.yandex.practicum.filmorate.storage;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.utils.IdGenerator;

import java.util.*;


@Component
@Slf4j
@Data
public class InMemoryFilmStorage implements FilmStorage {
    private Map<Integer, Film> films = new HashMap<>();


    @Override
    public Film createFilm(Film addFilm) {
       /* addFilm.setId(filmsId);
        filmsId++;*/
        if (addFilm.getId() == 0) {
            addFilm.setId(IdGenerator.nextFilmId());
        }
        films.put(addFilm.getId(), addFilm);
        return addFilm;
    }

    @Override
    public Film updateFilm(Film newFilm) {
        final Film oldFilm = getFilmById(newFilm.getId());
        if (oldFilm.equals(newFilm)) return newFilm;
        return newFilm;
    }

    @Override
    public List<Film> getFilms() {
        return new ArrayList<>(films.values());
    }

    @Override
    public Film getFilmById(int id) {

        return films.get(id);

    }

}