package com.example.BookMicro;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            return ResponseEntity.ok(book);

        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{action}/{id}")
    public ResponseEntity<?> updateBookAvailability(@PathVariable Long id, @PathVariable String action) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        if (action.equals("return")) {
            if (!book.isAvailability()) {
                bookService.updateBookAvailability(id, true);
                return new ResponseEntity<>(HttpStatusCode.valueOf(200));
            } else return ResponseEntity.badRequest().body("ALREADY RETURNED");
        }
        if (action.equals("loan")) {
            if (book.isAvailability()) {
                bookService.updateBookAvailability(id, true);
                return new ResponseEntity<>(HttpStatusCode.valueOf(200));
            } else return ResponseEntity.badRequest().body("NOT AVAILABLE");
        }
        return ResponseEntity.badRequest().body("UNKNOWN ERROR FROM updateBookAvailability in BookMicro");
    }
}
