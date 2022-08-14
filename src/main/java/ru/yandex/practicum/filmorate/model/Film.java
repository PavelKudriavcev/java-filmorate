package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Film {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private LocalDate releaseDate;
    @NonNull
    private int duration;
}
