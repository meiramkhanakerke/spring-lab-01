package kz.iitu.springlab.catalog;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findAll(String author) {
        if (author == null || author.isBlank()) {
            return repository.findAll();
        }
        return repository.findAll().stream()
                .filter(b -> b.author().toLowerCase().contains(author.toLowerCase()))
                .toList();
    }

    // ВАРИАНТ 6: Поиск книг по году выпуска
    public List<Book> findByYear(Integer year) {
        if (year == null) {
            return repository.findAll();
        }
        return repository.findAll().stream()
                .filter(b -> b.year() == year)
                .toList();
    }

    public Optional<Book> findById(long id) {
        return repository.findById(id);
    }

    public Book create(Book book) {
        return repository.save(book);
    }

    public Optional<Book> update(long id, Book book) {
        if (repository.findById(id).isEmpty()) {
            return Optional.empty();
        }
        Book updatedBook = new Book(id, book.title(), book.author(), book.year());
        return Optional.of(repository.save(updatedBook));
    }

    public boolean deleteById(long id) {
        return repository.deleteById(id);
    }
}