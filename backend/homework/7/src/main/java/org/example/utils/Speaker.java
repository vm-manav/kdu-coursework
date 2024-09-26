package org.example.utils;

public class Speaker {
    private double speakerPrice;
    private String speakerName;

    public void setName(String speakerName) {
        this.speakerName = speakerName;
    }

    public String getName() {
        return speakerName;
    }

    public void setPrice(double speakerPrice) {
        this.speakerPrice = speakerPrice;
    }

    public double getPrice() {
        return speakerPrice;
    }
    public Speaker(String name, double price){
        this.speakerName=name;
        this.speakerPrice=price;
    }
}
