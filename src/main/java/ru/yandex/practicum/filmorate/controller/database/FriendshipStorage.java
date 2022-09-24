package ru.yandex.practicum.filmorate.controller.database;

import ru.yandex.practicum.filmorate.model.Friendship;

import java.util.Collection;

public interface FriendshipStorage {

    Collection<Integer> getFriendsIds(Integer id);

    void create(Friendship friendship);

    void delete(Friendship friendship);
}
