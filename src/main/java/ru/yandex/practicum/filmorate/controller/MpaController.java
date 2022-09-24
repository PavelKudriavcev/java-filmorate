package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.yandex.practicum.filmorate.model.RatingMPA;
import ru.yandex.practicum.filmorate.service.MPAService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mpa")
public class MpaController {
    private final MPAService service;

    @GetMapping
    List<RatingMPA> getAll() {
        return service.getAllMpa();
    }

    @GetMapping("{id}")
    RatingMPA get(@PathVariable int id) {
        RatingMPA ratingMPA = service.getMpaById(id);
        if (ratingMPA == null) throw new NoSuchElementException();
        return ratingMPA;
    }
}

