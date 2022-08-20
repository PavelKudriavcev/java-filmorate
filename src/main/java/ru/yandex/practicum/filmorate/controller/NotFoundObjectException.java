package ru.yandex.practicum.filmorate.controller;

public class NotFoundObjectException extends NullPointerException {

    public NotFoundObjectException(String s) {
        super(s);
    }
}