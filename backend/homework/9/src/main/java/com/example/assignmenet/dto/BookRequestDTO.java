package com.example.assignmenet.dto;

import lombok.Data;
import lombok.NonNull;

import java.lang.ref.PhantomReference;

@Data
public class BookRequestDTO {
    @NonNull
    private Integer bookId;
    @NonNull
    private String bookName;
    @NonNull
    private String bookAuthor;
    @NonNull
    private Double price;
    @NonNull
    private Integer quantity;
}
