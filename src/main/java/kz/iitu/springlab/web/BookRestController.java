package kz.iitu.springlab.web;

import kz.iitu.springlab.catalog.BookService;
import kz.iitu.springlab.catalog.Genre;
import kz.iitu.springlab.web.dto.BookDto;
import kz.iitu.springlab.web.dto.BookMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookRestController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    public Page<BookDto> getAllBooks(
            @RequestParam(required = false) Genre genre,
            @PageableDefault(size = 5, sort = "title") Pageable pageable) {

        if (genre != null) {
            return bookService.findByGenre(genre, pageable).map(bookMapper::toDto);
        }
        return bookService.findAll(pageable).map(bookMapper::toDto);
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookMapper.toDto(bookService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}