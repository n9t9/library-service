package com.sample.bookservice;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @PostMapping
        public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String bookId) {
        Book book = bookService.getBookById(bookId);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public List<Book> getBooks()
    {
        return bookService.getBooks();
    }

    @GetMapping("/{id}/available")
    public int getAvailableBooks(@PathVariable("id") String id)
    {
        return bookService.getAvailableCopies(id);
    }

    @PutMapping("/{id}/borrowed/{type}")
    ResponseEntity<Book> updateBook(@PathVariable("id") String id, @PathVariable("type") String type)
    {
        boolean borrowed = type.equals("yes");
        Book book = bookService.updateBook(id, borrowed);
        return ResponseEntity.ok(book);
    }
}