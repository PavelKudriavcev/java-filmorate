package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@Validated
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    protected void isValidUser(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
    }

    @PostMapping
    public User createUser(@RequestBody @Valid User addUser) {
        log.debug("создание пользователя");
        isValidUser(addUser);
        return userService.createUser(addUser);
    }

    @PutMapping
    public User updateUser(@RequestBody @Valid User addUser) {
        log.debug("обновление пользователя");
        isValidUser(addUser);
        return userService.updateUser(addUser);
    }

    @GetMapping
    public List<User> getUsers() {
        log.debug("получение списка всех пользователей");
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getFilmById(@PathVariable int id) {
        log.debug("получение фильма по ID");
        return userService.getUserById(id);
    }


    @PutMapping("/{id}/friends/{friendId}")
    public void addFriends(@PathVariable int id, @PathVariable int friendId) {
        log.debug("добавление в друзья");
        userService.addFriends(id, friendId);
    }

    //удалить пользователя из друзей
    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable int id, @PathVariable int friendId) {
        log.debug("удаление из друзей");
        userService.deleteFriends(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public List<User> usersFriends(@PathVariable int id) {
        log.debug("возвращаем список пользователей, являющихся его друзьями");
        return userService.usersFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> commonFriend(@PathVariable int id, @PathVariable int otherId) {
        log.debug("список друзей, общих с другим пользователем");
        return userService.commonFriends(id, otherId);
    }
}