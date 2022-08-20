package ru.yandex.practicum.filmorate.storage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

public interface UserStorage {

    User createUser(@RequestBody @Valid User addUser);


    User updateUser(@RequestBody @Valid User addUser);

    List<User> getUsers();

    User getUserById(int id);

    void addFriend(int userId, int friendId);

    void deleteFriend(int userId, int friendId);

    List<User> usersFriends(int id);
    List<User> commonFriends(int idUser, int idOther);
}
