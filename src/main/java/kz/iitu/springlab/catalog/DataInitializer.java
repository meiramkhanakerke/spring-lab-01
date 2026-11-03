package kz.iitu.springlab.catalog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            TagRepository tagRepository) {
        return args -> {
            // Запускать вставку тестовых данных только если база данных пуста
            if (tagRepository.count() == 0) {
                Tag t1 = tagRepository.save(new Tag("Java"));
                Tag t2 = tagRepository.save(new Tag("Spring"));
                Tag t3 = tagRepository.save(new Tag("Database"));
                Tag t4 = tagRepository.save(new Tag("Backend"));

                Author a1 = new Author("Robert Martin", "USA");
                Book b1 = new Book("Clean Code", Genre.TECHNICAL, 2008);
                b1.setTags(Set.of(t1, t4));
                Book b2 = new Book("Clean Architecture", Genre.TECHNICAL, 2017);
                b2.setTags(Set.of(t4));
                Book b3 = new Book("Clean Agile", Genre.TECHNICAL, 2019);
                a1.addBook(b1);
                a1.addBook(b2);
                a1.addBook(b3);
                authorRepository.save(a1);

                Author a2 = new Author("Craig Walls", "USA");
                Book b4 = new Book("Spring in Action", Genre.TECHNICAL, 2022);
                b4.setTags(Set.of(t1, t2, t4));
                Book b5 = new Book("Spring Boot in Action", Genre.TECHNICAL, 2016);
                b5.setTags(Set.of(t2));
                Book b6 = new Book("Spring Data", Genre.TECHNICAL, 2012);
                b6.setTags(Set.of(t2, t3));
                a2.addBook(b4);
                a2.addBook(b5);
                a2.addBook(b6);
                authorRepository.save(a2);

                Author a3 = new Author("Joshua Bloch", "USA");
                Book b7 = new Book("Effective Java", Genre.TECHNICAL, 2018);
                b7.setTags(Set.of(t1));
                Book b8 = new Book("Java Puzzlers", Genre.TECHNICAL, 2005);
                a3.addBook(b7);
                a3.addBook(b8);
                authorRepository.save(a3);
            }
        };
    }
}