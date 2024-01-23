package org.example.utils;

public class Tyre {
    private double tyrePrice;
    private String tyreName;

    public void setName(String tyreName) {
        this.tyreName = tyreName;
    }

    public String getName() {
        return tyreName;
    }

    public void setPrice(double tyrePrice) {
        this.tyrePrice = tyrePrice;
    }

    public double getPrice() {
        return tyrePrice;
    }
    public Tyre(String name , double price){
        this.tyreName=name;
        this.tyrePrice=price;
    }
}
