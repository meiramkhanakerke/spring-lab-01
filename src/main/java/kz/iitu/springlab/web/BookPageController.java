package kz.iitu.springlab.web;

import kz.iitu.springlab.catalog.Book;
import kz.iitu.springlab.catalog.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookPageController {

    private final BookService service;

    public BookPageController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public String listBooks(@RequestParam(required = false) String author,
                            @RequestParam(required = false) Integer year,
                            Model model) {
        model.addAttribute("title", "Books Catalogue");
        model.addAttribute("author", author);
        model.addAttribute("year", year);

        if (year != null) {
            model.addAttribute("books", service.findByYear(year));
        } else {
            model.addAttribute("books", service.findAll(author));
        }

        return "books/list";
    }

    @PostMapping
    public String addBook(@RequestParam String title,
                          @RequestParam String author,
                          @RequestParam int year,
                          RedirectAttributes redirectAttributes) {
        service.create(new Book(null, title, author, year));
        redirectAttributes.addFlashAttribute("message", "Book successfully added!");
        return "redirect:/books";
    }
}