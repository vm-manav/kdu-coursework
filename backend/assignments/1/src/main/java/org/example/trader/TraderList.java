package org.example.trader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TraderList {
    public static final ArrayList<Traders> tradersArray=new ArrayList<>();

    public List<Traders> getTradersArray(){
        return tradersArray;
    }
    public Traders getTraderByWalletAddress(String walletAddress) {
        for (Traders currentTrader : tradersArray) {
            if (Objects.equals(currentTrader.getWalletAddress(), walletAddress)) {
                return currentTrader;
            }
        }
        return null;
    }
    public void addTrader(Traders trader){
        this.tradersArray.add(trader);
    }
    public void updateTrader(Traders trader ,String coinName,double price,long quantity,boolean isBuy) {
        if(isBuy) {
            trader.setProfit(trader.getProfit()+(quantity*price*-1));
        } else {
            trader.setProfit(trader.getProfit()+(quantity*price));
        }
        trader.updatePortfolio(coinName,quantity,isBuy);
    }
    public boolean isSafeToSellQuantity(Traders trader,String coinName,long quantity) {
        return trader.getPortfolio().containsKey(coinName) && (trader.getPortfolio().get(coinName) >= quantity);
    }
}
