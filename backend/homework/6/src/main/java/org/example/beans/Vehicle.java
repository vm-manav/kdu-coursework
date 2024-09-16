package org.example.beans;

public class Vehicle {
    private double price;
    private Speaker speaker;
    private Tyre tyre;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }
    public double generateRandomPrice() {
        return Math.random()*100000;
    }
    public Vehicle(Speaker speaker,Tyre tyre) {
        this.price=speaker.getPrice()+tyre.getPrice()+generateRandomPrice();
        this.tyre=tyre;
        this.speaker=speaker;
    }
}
