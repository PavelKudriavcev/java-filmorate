package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int id;
    @Email
    private String email;
    @NotBlank
    private String login;
    private String name;
    @NonNull
    private LocalDate birthday;
}
