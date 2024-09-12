package org.example.coin;

import java.util.concurrent.atomic.AtomicLong;

public class Coins {
    private int id;
    private int rank;
    private double price;
    private long maxQuantity;
    private String name;
    private String symbol;
    private static final AtomicLong coinsInMarket=new AtomicLong();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(long quantity) {
        this.maxQuantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getCoinsInMarket() {
        return coinsInMarket.get();
    }

    public void setCoinsInMarket(long newCoinsInMarket) {
        coinsInMarket.set(newCoinsInMarket);
    }

    public Coins(String id, String rank, String name, String symbol, String price, String quantity){
        this.id=Integer.parseInt(id);
        this.rank=Integer.parseInt(rank);
        this.name=name;
        this.symbol=symbol;
        this.price=Double.parseDouble(price);
        this.maxQuantity=Long.parseLong(quantity);
        coinsInMarket.set(Long.parseLong(quantity));
    }
    public Coins(int rank, String name, String symbol, double price, long quantity){
        this.id= 0;
        this.rank=rank;
        this.name=name;
        this.symbol=symbol;
        this.price=price;
        this.maxQuantity=quantity;
        coinsInMarket.set(0);
    }
}
