package ru.yandex.practicum.filmorate.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@Data
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody @Valid User addUser) {

        return userService.createUser(addUser);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        return user.getId() < 1 ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return id < 1 ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }


    @PutMapping("/users/{id}/friends/{friendId}")
    public void addFriends(@PathVariable int id, @PathVariable int friendId) {
        userService.addFriends(id, friendId);
    }

    //удалить пользователя из друзей
    @DeleteMapping("/users/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable int id, @PathVariable int friendId) {
        userService.deleteFriends(id, friendId);
    }

    @GetMapping("/users/{id}/friends")
    public List<User> usersFriends(@PathVariable int id) {
        return userService.usersFriends(id);
    }

    @GetMapping("/users/{id}/friends/common/{otherId}")
    public List<User> commonFriend(@PathVariable int id, @PathVariable int otherId) {
        return userService.commonFriends(id, otherId);
    }
}