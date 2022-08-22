package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.controller.Exceptions.NotFoundObjectException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.*;

@Component
public class InMemoryUserStorage implements UserStorage {
    private Map<Integer, User> users = new HashMap<>();
    private int usersId = 1;
    @Override
    public User createUser(User addUser) {
        addUser.setId(usersId++);
        users.put(addUser.getId(), addUser);
        return addUser;
    }
    @Override
    public User updateUser(User addUser) {
        if (users.containsKey(addUser.getId())) {
            users.put(addUser.getId(), addUser);
            return addUser;
        } else {
            throw new NotFoundObjectException("Такого пользователя нет");
        }
    }
    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }
    @Override
    public User getUserById(int id) {
        if (!users.containsKey(id)) {
            throw new NotFoundObjectException("Такого опльзователя нет");
        } else {
            return users.get(id);
        }
    }
    @Override
    public void addFriend(int userId, int friendId) {
        getUserById(userId).getFriends().add(getUserById(friendId).getId());
        getUserById(friendId).getFriends().add(userId);
    }
    @Override
    public void deleteFriend(int userId, int friendId) {
        getUserById(userId).getFriends().add(getUserById(friendId).getId());
        getUserById(friendId).getFriends().add(userId);
    }
    @Override
    public List<User> usersFriends(int id) {
        User user = getUserById(id);
        List<User> usersFriends = new ArrayList<>();
        for(int friendId : user.getFriends()){
            usersFriends.add(users.get(friendId));
        }
        return usersFriends;
    }
    public List<User> commonFriends(int idUser1, int idUser2) {
        List<User> commonFriends = new ArrayList<>();
        User user1 = getUserById(idUser1);
        User user2 = getUserById(idUser2);
        Set<Integer> user1Friends = user1.getFriends();
        Set<Integer> user2Friends = user2.getFriends();
        for (int userid : user1Friends){
            if (user2Friends.contains(userid)){
                commonFriends.add(users.get(userid));
            }
        }
        return commonFriends;
    }
}