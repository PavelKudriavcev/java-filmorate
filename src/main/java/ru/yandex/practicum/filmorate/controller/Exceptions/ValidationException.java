package ru.yandex.practicum.filmorate.controller.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidationException extends ResponseStatusException {
    public ValidationException(HttpStatus status, String reason) {
        super(status, reason);
    }
}