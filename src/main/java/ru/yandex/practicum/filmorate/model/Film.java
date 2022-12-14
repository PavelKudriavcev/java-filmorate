package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Film {
    private int id;
    @NotBlank
    private String name;
    @Length(max = 200)
    private String description;
    @NotNull
    private LocalDate releaseDate;
    @Positive
    private int duration;
    private Set<Integer> likes = new HashSet<>();   // "один пользователь — один лайк". Массив хранит id пользователя

    public int getCountLike() {
        return likes.size();
    }
}
