package com.example.assignmenet.dto;

import lombok.Data;

@Data
public class BookResponseDTO {
    private String bookName;
    private String bookAuthor;
    private double price;
    private int quantity;
}
