package kz.iitu.springlab.web;

import kz.iitu.springlab.catalog.Book;
import kz.iitu.springlab.catalog.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService service;

    public BookRestController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> getAll(@RequestParam(required = false) String author,
                             @RequestParam(required = false) Integer year) {
        if (year != null) {
            return service.findByYear(year);
        }
        return service.findAll(author);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        Book created = service.create(book);
        return ResponseEntity.created(URI.create("/api/books/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable long id, @RequestBody Book book) {
        return service.update(id, book)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        if (service.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
