package kz.iitu.springlab.web;

import jakarta.validation.constraints.*;
import kz.iitu.springlab.catalog.Genre;

public class BookForm {
    private Long id;

    @NotBlank
    @Size(max = 120)
    private String title;

    @NotBlank
    private String author;

    @Min(1450)
    @Max(2100)
    private int year;

    @NotNull
    private Genre genre;

    public BookForm() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }
}