package kz.iitu.springlab.web;

import kz.iitu.springlab.catalog.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PageExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleBookNotFound(BookNotFoundException ex) {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>404 - Book Not Found</title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 60px; background-color: #f8f9fa; }
                    .error-card { background: white; padding: 30px; border-radius: 8px; border-left: 6px solid #dc3545; box-shadow: 0 4px 10px rgba(0,0,0,0.1); max-width: 520px; }
                    h1 { color: #dc3545; margin-top: 0; font-size: 24px; }
                    p { font-size: 16px; color: #333; line-height: 1.5; }
                    a { display: inline-block; margin-top: 15px; color: #0d6efd; text-decoration: none; font-weight: bold; }
                </style>
            </head>
            <body>
                <div class="error-card">
                    <h1>404 - Book Not Found</h1>
                    <p>%s</p>
                    <a href="/books/new">← Back to Form</a>
                </div>
            </body>
            </html>
            """.formatted(ex.getMessage());
    }
}