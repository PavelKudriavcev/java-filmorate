package ru.yandex.practicum.filmorate.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Slf4j
@Component
@Builder

public class User {
    private int id;
    @NotNull
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z\\d]*$")
    private String login;
    private String name;
    @Past(message = "поле *birthday* не может указывать на будущую дату")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    //private Set<Integer> friends = new HashSet<>();

    public User(String login, String email, String name, LocalDate birthday) {
        this.login = login;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
    }
}
