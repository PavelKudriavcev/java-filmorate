package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@Validated
@RequestMapping("/users")
public class UserController {
    List<User> users = new ArrayList<>();
    int usersId = 1;

    protected void isValidUser(User user) {

        if ((user.getEmail().length() <= 5) || (!user.getEmail().contains("@"))) {
            throw new ValidationException("электронная почта не может быть пустой и должна содержать символ @");
        }
        if (((user.getLogin()).isEmpty()) || ((user.getLogin()).contains(" "))) {
            throw new ValidationException("логин не может быть пустым и содержать пробелы;");
        }
        if (LocalDate.now().isBefore(user.getBirthday())) {
            throw new ValidationException("дата рождения не может быть в будущем.");
        }
    }

    @PostMapping
    public User createUser(@RequestBody @Valid User addUser) {
        log.debug("создание пользователя");
        isValidUser(addUser);
        if (addUser.getName().isEmpty()){
            addUser.setName(addUser.getLogin());
        }
        addUser.setId(usersId++);
        users.add(addUser);
        return addUser;
    }
    @PutMapping
    public User updateUser(@RequestBody @Valid User addUser) {
        log.debug("обновление пользователя");
        isValidUser(addUser);
        for (User userUp : users) {
            if (userUp.getId() == addUser.getId()) {
                users.remove(userUp);
                users.add(addUser);
            }else {
                throw new ValidationException( "Такого пользователя нет");
            }
        }
        return addUser;
    }
    @GetMapping
    public List<User> getUsers() {
        log.debug("получение списка всех пользователей");
        return users;
    }
}
