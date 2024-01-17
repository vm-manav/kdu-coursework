package org.example;

import org.example.coin.Coins;
import org.example.coin.CoinsList;
import org.example.datafetch.HashCodeGenerator;
import org.example.trader.TraderList;
import org.example.trader.Traders;
import org.example.transaction.Transactions;

import java.util.Objects;

public class ExecuteTransaction implements Runnable {
    private Transactions transaction;
    private CoinsList coinsList;
    private TraderList traderList;

    public Transactions getTransaction() {
        return transaction;
    }

    public void setTransaction(Transactions transaction) {
        this.transaction = transaction;
    }

    public TraderList getTraderList() {
        return traderList;
    }

    public void setCoinsList(CoinsList coinsList) {
        this.coinsList = coinsList;
    }

    public CoinsList getCoinsList() {
        return coinsList;
    }


    public void setTraderList(TraderList traderList) {
        this.traderList = traderList;
    }
    public ExecuteTransaction(Transactions transaction,CoinsList coinsList,TraderList traderList) {
        this.transaction=transaction;
        this.coinsList=coinsList;
        this.traderList=traderList;
    }
    public ExecuteTransaction() {
    }

    private void scheduleTransaction(Transactions transaction, CoinsList coinsList, TraderList traderList) {
        if(Objects.equals(transaction.getType(), "BUY")) {
            buyTransaction(transaction, coinsList, traderList);

        } else if (Objects.equals(transaction.getType(), "SELL")) {
            sellTransaction(transaction,coinsList, traderList);

        } else if (Objects.equals(transaction.getType(), "UPDATE_PRICE")) {
            updatePriceTransaction(transaction,coinsList);

        } else if (Objects.equals(transaction.getType(), "ADD_VOLUME")) {
            addVolumeTransaction(transaction, coinsList);
        }
    }

    private synchronized void buyTransaction(Transactions transaction, CoinsList coinsList, TraderList traderList) {

        String traderWalletAddress=transaction.getData().getWalletAddress();

        Traders trader=traderList.getTraderByWalletAddress(traderWalletAddress);

        Coins coin=coinsList.getCoinBySymbol(transaction.getData().getCoin());

        if(coin==null){
            return;
        }
        if(trader==null){
            return;
        }
        double price=coinsList.getPriceForCoin(coin);
        if(coinsList.isSafeToBuyCoin(coin,transaction.getData().getQuantity())) {
            traderList.updateTrader(trader,transaction.getData().getCoin(),price,transaction.getData().getQuantity(),true);
            coinsList.buyCoins(coin,transaction.getData().getQuantity());
            HashCodeGenerator hashCodeGenerator=new HashCodeGenerator();
            hashCodeGenerator.getBlockHash();
            notifyAll();
        }

    }
    public synchronized void sellTransaction(Transactions transaction, CoinsList coinsList, TraderList traderList) {

        String traderWalletAddress=transaction.getData().getWalletAddress();
        Traders trader=traderList.getTraderByWalletAddress(traderWalletAddress);
        Coins coin=coinsList.getCoinBySymbol(transaction.getData().getCoin());
        if(coin==null){
            return;
        }
        if(trader==null){
            return;
        }
        double price=coinsList.getPriceForCoin(coin);
        while (!coinsList.isSafeToSell(coin,transaction.getData().getQuantity()) ||
                !traderList.isSafeToSellQuantity(trader,transaction.getData().getCoin(),transaction.getData().getQuantity())) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        traderList.updateTrader(trader,transaction.getData().getCoin(),price,transaction.getData().getQuantity(),false);
        coinsList.sellCoins(coin,transaction.getData().getQuantity());

        HashCodeGenerator hashCodeGenerator=new HashCodeGenerator();
        hashCodeGenerator.getBlockHash();
        notifyAll();
    }
    private synchronized void updatePriceTransaction(Transactions transaction,CoinsList coinsList) {
        Coins coin=coinsList.getCoinBySymbol(transaction.getData().getCoin());
        if(coin==null){
            return;
        }
        coinsList.updatePrice(coin,transaction.getData().getPrice());
    }
    private synchronized void addVolumeTransaction(Transactions transaction,CoinsList coinsList) {
        Coins coin=coinsList.getCoinBySymbol(transaction.getData().getCoin());
        if(coin==null){
            return;
        }
        if(coinsList.isSafeToAddVolume(coin,transaction.getData().getQuantity())) {
            coinsList.addVolume(coin,transaction.getData().getQuantity());
            notifyAll();
        }
    }

    @Override
    public void run() {
        scheduleTransaction(transaction,coinsList,traderList);
    }
}
