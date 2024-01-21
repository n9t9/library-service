package com.sample.bookservice;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(String bookId) {
        return bookRepository.findById(bookId).get();
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public int getAvailableCopies(String BookId) {
        return bookRepository.findById(BookId).get().getAvailable_copies();
    }

    @Override
    public Book updateBook(String BookId, Boolean borrowed) {
        Book book = bookRepository.findById(BookId).get();
        int available = book.getAvailable_copies();
        if (available < 1 && borrowed) {
            return book;
        } else if (borrowed) {
            book.setAvailable_copies(available - 1);
        } else {
            book.setAvailable_copies(available + 1);
        }
        return bookRepository.save(book);
    }
}