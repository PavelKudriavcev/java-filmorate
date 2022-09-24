package ru.yandex.practicum.filmorate.storage;

import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.List;

public interface UserStorage {

    User getById(int id);


    User createUser(User addUser);


    User updateUser(User addUser);

    List<User> getUsers();


}
