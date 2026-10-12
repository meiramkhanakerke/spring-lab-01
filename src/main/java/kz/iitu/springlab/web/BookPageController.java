package kz.iitu.springlab.web;

import jakarta.validation.Valid;
import kz.iitu.springlab.catalog.Book;
import kz.iitu.springlab.catalog.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("form", new BookForm());
        model.addAttribute("genres", Genre.values());
        return "books/form";
    }

    @PostMapping
    public String addBook(@Valid @ModelAttribute("form") BookForm form,
                          BindingResult binding,
                          Model model,
                          RedirectAttributes redirectAttributes) {

        if (binding.hasErrors()) {
            model.addAttribute("genres", Genre.values());
            return "books/form";
        }

        Book saved = service.create(new Book(null, form.getTitle(), form.getAuthor(), form.getYear()));
        redirectAttributes.addFlashAttribute("message", "Book «" + saved.title() + "» has been added!");
        return "redirect:/books";
    }
}
