package ru.yandex.practicum.filmorate.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@NoArgsConstructor
public class UserService {
     UserStorage userStorage;
    FilmStorage filmStorage;

    @Autowired
    public UserService(UserStorage userStorage, FilmStorage filmStorage) {
        this.userStorage = userStorage;
        this.filmStorage = filmStorage;
    }

    public User createUser(User user) {

        return userStorage.createUser(user);
    }

    public User updateUser(User user) {

        return userStorage.updateUser(user);
    }

    public List<User> getUsers() {
        return userStorage.getUsers();
    }

    //получить пользователя по id
    public User getUserById(int id) {

        return userStorage.getUserById(id);
    }

    public void addFriends(int userId, int friendId) {
        userStorage.addFriend(userId, friendId);
    }

    public void deleteFriends(int userId, int friendId) {
        userStorage.deleteFriend(userId, friendId);
    }

    public List<User> usersFriends(int id) {
        User user = getUserById(id);
        List<User> userFriends = new ArrayList<>();
        user.getFriends().stream().forEach(e -> userFriends.add(getUserById(e)));
        //return userFriends;
        return userStorage.usersFriends(id);
    }

    public List<User> commonFriends(int idUser, int idOther) {
        return userStorage.commonFriends(idUser, idOther);
    }
}
