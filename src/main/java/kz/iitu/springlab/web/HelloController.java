package kz.iitu.springlab.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;

@RestController
@RequestMapping("/api")
public class HelloController {

    @Value("${app.owner:unknown}")
    private String owner;

    @GetMapping("/hello")
    public Greeting hello(@RequestParam(defaultValue = "world") String name) {
        return new Greeting("Hello, " + name + "!", owner, LocalDateTime.now());
    }

    @GetMapping("/info")
    public Info info() {
        return new Info(owner,
                System.getProperty("java.version"),
                Runtime.getRuntime().availableProcessors());
    }

    // Individual Task (Variant 7: wordcount)
    @GetMapping("/wordcount")
    public WordCount wordCount(@RequestParam(defaultValue = "") String text) {
        if (text.isBlank()) {
            return new WordCount(0, 0, "");
        }
        String[] words = text.trim().split("\\s+");
        int characterCount = text.length();
        String longestWord = Arrays.stream(words)
                .max(Comparator.comparingInt(String::length))
                .orElse("");

        return new WordCount(words.length, characterCount, longestWord);
    }

    public record Greeting(String message, String owner, LocalDateTime timestamp) { }

    public record Info(String owner, String javaVersion, int cpuCores) { }

    public record WordCount(int wordCount, int characterCount, String longestWord) { }
}