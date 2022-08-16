package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Validated
@RequestMapping("/users")
public class UserController {
    private Map<Integer, User> users = new HashMap<>();
    private int usersId = 1;

    protected void isValidUser(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
    }

    @PostMapping
    public User createUser(@RequestBody @Valid User addUser) {
        log.debug("создание пользователя");
        isValidUser(addUser);
        addUser.setId(usersId);
        usersId++;
        users.put(addUser.getId(), addUser);
        return addUser;
    }

    @PutMapping
    public User updateUser(@RequestBody @Valid User addUser) {
        log.debug("обновление пользователя");
        isValidUser(addUser);
        if (users.containsKey(addUser.getId())) {
            users.put(addUser.getId(), addUser);
            return addUser;
        } else {
            throw new ValidationException("Такого пользователя нет");
        }
    }

    @GetMapping
    public List<User> getUsers() {
        log.debug("получение списка всех пользователей");
        return new ArrayList<>(users.values());
    }
}
