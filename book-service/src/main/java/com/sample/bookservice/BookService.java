package com.sample.bookservice;

public interface BookService {
    Book saveBook(Book book);

    Book getBookById(Long departmentId);
}
