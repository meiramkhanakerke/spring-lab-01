package kz.iitu.springlab.web.dto;

import kz.iitu.springlab.catalog.Book;
import kz.iitu.springlab.catalog.Genre;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDto toDto(Book book) {
        if (book == null) return null;
        String authorName = book.getAuthor() != null ? book.getAuthor().getName() : null;
        return new BookDto(
                book.getId(),
                book.getTitle(),
                authorName,
                book.getYear(),
                book.getGenre()
        );
    }
}