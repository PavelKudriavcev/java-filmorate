package ru.yandex.practicum.filmorate.utils;

public class IdGenerator {
    private static int userId = 0;
    private static int filmId = 0;

    public static int nextUserId() {
        return ++userId;
    }

    public static int nextFilmId() {
        return ++filmId;
    }
}

