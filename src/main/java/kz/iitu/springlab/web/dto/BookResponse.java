package kz.iitu.springlab.web.dto;

import kz.iitu.springlab.catalog.Genre;

public record BookResponse(
        Long id,
        String title,
        String author,
        int year,
        Genre genre
) {}