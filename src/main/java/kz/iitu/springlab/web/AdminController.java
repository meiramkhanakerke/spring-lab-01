package kz.iitu.springlab.web;

import kz.iitu.springlab.security.AppUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AppUserRepository userRepository;

    public AdminController(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin";
    }
}