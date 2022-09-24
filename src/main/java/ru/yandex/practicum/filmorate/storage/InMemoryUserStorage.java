package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.utils.IdGenerator;

import java.util.*;

@Component
public class InMemoryUserStorage implements UserStorage {
    private Map<Integer, User> users = new HashMap<>();


    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User getById(int id) {
        return users.get(id);
    }

    @Override
    public User createUser(User user) {
        /*if (user.getName() == null || user.getName().isBlank() || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        user.setId(IdGenerator.nextUserId());
        users.put(user.getId(), user);
        return users.get(user.getId());*/
        if (user.getName() == null || user.getName().isBlank() || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        if (user.getId() == 0) {
            user.setId(IdGenerator.nextUserId());
        }
        users.put(user.getId(), user);
        return users.get(user.getId());
    }

    @Override
    public User updateUser(User user) {
        if (user.getName() == null || user.getName().isBlank() || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }

        if (!users.containsKey(user.getId()) && user.getId() >= 1) {
            users.put(user.getId(), user);
        } else {
            users.put(user.getId(), user);
        }
        return users.get(user.getId());
    }
}