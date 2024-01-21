package com.sample.subscriptonservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String book_id;
    private String book_name;
    private String author;
    private int available_copies;
    private int total_copies;

}