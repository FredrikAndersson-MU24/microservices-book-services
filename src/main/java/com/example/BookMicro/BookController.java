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
        Book book = bookService.getBookById(id);
        if ( book != null ) {
            return ResponseEntity.ok(book);

        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
      return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PutMapping ("/{id}/{availability}")
    public void updateBookAvailability(@PathVariable Long id, @PathVariable boolean availability) {
        bookService.updateBookAvailability(id, availability);
    }
}
