package com.example.BookMicro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
      return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping
    public void createBook(@RequestBody Book book){
        bookService.addBook(book);
        bookService.saveBook(book);
    }

    @PutMapping ("/{id}/{availability}")
    public void updateBookAvailability(@PathVariable Long id, @PathVariable boolean availability) {
        bookService.updateBookAvailability(id, availability);
    }
}
