package org.example.coin;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CoinsList{
    private final ArrayList<Coins> coinsArray=new ArrayList<>();

    public List<Coins> getCoinsArray() {
        return coinsArray;
    }

    public void addCoin(Coins coin){
        this.coinsArray.add(coin);
    }
    public synchronized double getPriceForCoin(Coins coin) {
        return coin.getPrice();
    }
    public Coins getCoinBySymbol(String coinSymbol) {
        for (Coins currentCoin : coinsArray) {
            if (Objects.equals(currentCoin.getSymbol(), coinSymbol)) {
                return currentCoin;
            }
        }
        return null;
    }
    public synchronized void updatePrice(Coins coin, Double coinPrice) {
        coin.setPrice(coinPrice);
    }
    public synchronized boolean isSafeToBuyCoin(Coins coin, long coinQuantity) {
        return coin.getCoinsInMarket() - coinQuantity >= 0;
    }
    public synchronized void buyCoins(Coins coin, long coinQuantity) {
        coin.setCoinsInMarket(coin.getCoinsInMarket()-coinQuantity);
    }
    public synchronized boolean isSafeToSell(Coins coin, long coinQuantity) {
        return coin.getCoinsInMarket() + coinQuantity <= coin.getMaxQuantity();
    }

    public synchronized void sellCoins(Coins coin, long coinQuantity) {
        coin.setCoinsInMarket(coin.getCoinsInMarket()+coinQuantity);
    }
    public synchronized boolean isSafeToAddVolume(Coins coin, long coinQuantity) {
        return coin.getCoinsInMarket() + coinQuantity <= coin.getMaxQuantity();
    }
    public synchronized void addVolume(Coins coin, long coinQuantity) {
        coin.setCoinsInMarket(coin.getCoinsInMarket()+coinQuantity);
    }
}
