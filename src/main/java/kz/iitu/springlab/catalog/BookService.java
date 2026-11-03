package kz.iitu.springlab.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAllWithAuthor(pageable);
    }

    public Page<Book> findByGenre(Genre genre, Pageable pageable) {
        return bookRepository.findByGenre(genre, pageable);
    }

    public List<Book> findAll() {
        return bookRepository.findAllWithAuthor();
    }

    public List<Book> findByAuthor(String authorName) {
        if (authorName == null || authorName.isBlank()) {
            return bookRepository.findAllWithAuthor();
        }
        return bookRepository.findAllWithAuthor().stream()
                .filter(b -> b.getAuthor() != null && b.getAuthor().getName().equalsIgnoreCase(authorName))
                .toList();
    }

    public List<Book> findByYear(int year) {
        return bookRepository.findAllWithAuthor().stream()
                .filter(b -> b.getYear() == year)
                .toList();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}