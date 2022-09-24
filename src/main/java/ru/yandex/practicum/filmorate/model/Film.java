package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.utils.IsAfter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Film {
    private int id;
    @NotBlank
    private String name;
    @Length(max = 200)
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @IsAfter(current = "1895-12-28")
    private LocalDate releaseDate;
    @Min(0)
    private int duration;

    @NonNull
    private RatingMPA mpa;
    private Set<Genre> genres;

    public Film(String name, String description, LocalDate releaseDate, int duration) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }
}
