package org.example.trader;
import java.util.HashMap;
import java.util.Map;

public class Traders {
    private int id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String walletAddress;
    private double profit;
    private final HashMap<String, Long> portfolio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public double getProfit() {
        return profit;
    }
    public void updatePortfolio(String coin,Long quantity,boolean isBuy) {
        if (portfolio.containsKey(coin)) {
            portfolio.computeIfPresent(coin, (key, existingQuantity) -> isBuy ? existingQuantity + quantity : existingQuantity - quantity);
        }
        portfolio.put(coin,quantity);

    }
    public Map<String,Long> getPortfolio(){
        return this.portfolio;
    }
    public void setProfit(double profit) {
        this.profit = profit;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }
    public Traders(String id, String firstname, String lastname, String phoneNumber, String walletAddress){
        this.id=Integer.parseInt(id);
        this.firstname=firstname;
        this.lastname=lastname;
        this.phoneNumber=phoneNumber;
        this.walletAddress=walletAddress;
        this.profit=0.0;
        this.portfolio=new HashMap<>();
    }
}
