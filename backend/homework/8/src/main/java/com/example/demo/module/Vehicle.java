package com.example.demo.module;

import lombok.Data;

@Data
public class Vehicle {
    private double price;
    private String vehicleName;
    private Speaker speaker;
    private Tyre tyre;
}
