package ru.yandex.practicum.filmorate.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.controller.Exceptions.UserDoesNotExistByIdException;
import ru.yandex.practicum.filmorate.controller.database.FriendshipStorage;
import ru.yandex.practicum.filmorate.controller.database.UserDbStorage;
import ru.yandex.practicum.filmorate.model.Friendship;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@NoArgsConstructor
public class UserService {
    private UserStorage userStorage;
    private FriendshipStorage friendshipStorage;

    @Autowired
    public UserService(UserDbStorage userStorage, FriendshipStorage friends) {
        this.friendshipStorage = friends;
        this.userStorage = userStorage;
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
        return userStorage.getById(id);
    }

    public void addFriends(int userId, int friendId) throws UserDoesNotExistByIdException {
        if (userId < 1 || friendId < 1) {
            log.info("ошибка из-за неверного id");
            throw new UserDoesNotExistByIdException("пользователь не может существовать с таким айди");
        }
        friendshipStorage.create(Friendship
                .builder()
                .user(getUserById(userId))
                .friend(getUserById(friendId))
                .build());
    }

    public void deleteFriends(int userId, int friendId) {
        if (userId < 1 || friendId < 1) {
            log.info("ошибка из-за неверного id");
            throw new UserDoesNotExistByIdException("пользователь не может существовать с таким айди");
        }
        friendshipStorage.delete(Friendship
                .builder()
                .user(getUserById(userId))
                .friend(getUserById(friendId))
                .build());
    }

    public List<User> commonFriends(int idUser, int idOther) throws UserDoesNotExistByIdException {
        Set<Integer> common = new HashSet<>(friendshipStorage.getFriendsIds(getUserById(idUser).getId()));
        common.retainAll(friendshipStorage.getFriendsIds(idOther));

        return common
                .stream()
                .map(userStorage::getById)
                .collect(Collectors.toList());
    }

    public List<User> usersFriends(int id) {
        if (id < 1) {
            log.info("ошибка из-за неверного id");
            throw new UserDoesNotExistByIdException("пользователь не может существовать с таким айди");
        }
        return friendshipStorage.getFriendsIds(getUserById(id).getId())
                .stream()
                .map(userStorage::getById)
                .collect(Collectors.toList());
    }
}