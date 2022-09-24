package ru.yandex.practicum.filmorate.controller.Exceptions;

public class UserDoesNotExistByIdException extends RuntimeException {


    public UserDoesNotExistByIdException(String message) {
        super(message);
    }
}