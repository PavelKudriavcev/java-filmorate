package ru.yandex.practicum.filmorate.controller.Exceptions;

public class NotFoundObjectException extends NullPointerException {

    public NotFoundObjectException(String s) {
        super(s);
    }
}