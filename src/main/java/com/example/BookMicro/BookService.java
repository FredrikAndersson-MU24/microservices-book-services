package com.example.BookMicro;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book newBook) {
        return bookRepository.save(newBook);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void updateBookAvailability(Long id, boolean available){
        Book book = getBookById(id);
        book.setAvailability(available);
        bookRepository.save(book);
    }
    
}
