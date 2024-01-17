package org.example.coin;
import java.util.ArrayList;
import java.util.List;

public class CoinsList{
    private final ArrayList<Coins> coinsArray=new ArrayList<>();

    public List<Coins> getCoinsArray() {
        return coinsArray;
    }

    public void addCoin(Coins coin){
        this.coinsArray.add(coin);
    }
    public double getPriceForCoin(Coins coin) {
        return coin.getPrice();
    }
    public Coins getCoinBySymbol(String coinSymbol) {
        for (Coins currentCoin : coinsArray) {
            if (currentCoin.getSymbol().equalsIgnoreCase(coinSymbol)) {
                return currentCoin;
            }
        }
        return null;
    }
    public void updatePrice(Coins coin, Double coinPrice) {
        coin.setPrice(coinPrice);
    }
    public boolean isSafeToBuyCoin(Coins coin, long coinQuantity) {
        return coin.getCoinsInMarket() - coinQuantity >= 0;
    }
    public void buyCoins(Coins coin, long coinQuantity) {
        coin.setCoinsInMarket(coin.getCoinsInMarket()-coinQuantity);
    }
    public boolean isSafeToSell(Coins coin, long coinQuantity) {
        return coin.getCoinsInMarket() + coinQuantity <= coin.getMaxQuantity();
    }

    public void sellCoins(Coins coin, long coinQuantity) {
        coin.setCoinsInMarket(coin.getCoinsInMarket()+coinQuantity);
    }
    public boolean isSafeToAddVolume(Coins coin, long coinQuantity) {
        return coin.getCoinsInMarket() + coinQuantity <= coin.getMaxQuantity();
    }
    public void addVolume(Coins coin, long coinQuantity) {
        coin.setCoinsInMarket(coin.getCoinsInMarket()+coinQuantity);
    }
}
