package com.sample.bookservice;

import java.util.List;

public interface BookService {
    Book saveBook(Book book);

    Book getBookById(String BookId);

    List<Book> getBooks();
    int getAvailableCopies(String BookId);
     Book updateBook(String BookId, Boolean borrowed);
}
