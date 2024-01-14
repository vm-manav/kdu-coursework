package org.example.datafetch;

import org.example.coin.Coins;
import org.example.coin.CoinsList;
import org.example.trader.TraderList;
import org.example.trader.Traders;


import java.util.*;
import java.util.logging.Logger;

public class HelperFunctions {
    private final Logger logger =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public void detailsOfCoin(String coinSearch, CoinsList coinsList) {
        for(Coins coin : coinsList.getCoinsArray()) {
            if(coin.getSymbol().equalsIgnoreCase(coinSearch)) {

                logger.info("Coin Details : ");
                logger.info("Coin name : "+coin.getName()+" , Coin id : "+coin.getId()+" , Coin rank : "+coin.getRank()+" , Coin Price : "+coin.getPrice()
                        +" , Coin Maximum Quantity Allowed : "+coin.getMaxQuantity()+" , Coin circulation in market : "+coin.getCoinsInMarket() );
                return;
            } else if (coin.getName().equalsIgnoreCase(coinSearch)) {
                logger.info("Coin Details : ");
                logger.info("Coin code : "+coin.getSymbol()+" , Coin id : "+coin.getId()+" , Coin rank : "+coin.getRank()+" , Coin Price : "+coin.getPrice()
                        +" , Coin Maximum Quantity Allowed : "+coin.getMaxQuantity()+" , Coin circulation in market : "+coin.getCoinsInMarket() );
                return;
            }
        }
        logger.info("Coin not found ");
    }

    public void showTraderPortfolio(String traderWalletAddress, TraderList traderList) {
        for(Traders trader:traderList.getTradersArray()) {
            if(Objects.equals(trader.getWalletAddress(), traderWalletAddress)) {
                Map<String, Long> portfolio=trader.getPortfolio();
                logger.info("Portfolio for "+trader.getFirstname()+" "+trader.getLastname()+" : ");
                for (Map.Entry<String,Long> mapElement : portfolio.entrySet()) {
                    logger.info("Coin : "+mapElement.getKey()+"  Quantity : "+mapElement.getValue() );
                }
                return;
            }
        }
        logger.info("Trader not Found");
    }
    public void showTraderProfit(String traderWalletAddress, TraderList traderList) {
        for(Traders trader:traderList.getTradersArray()) {
            if(Objects.equals(trader.getWalletAddress(), traderWalletAddress)) {
                logger.info("Profit/Loss for "+trader.getFirstname()+" "+trader.getLastname()+" : "+trader.getProfit());
                return;
            }
        }
        logger.info("Trader not Found");
    }

    public void showTopCoins(CoinsList coinsList,int number) {
        List<Coins> topCoins = coinsList.getCoinsArray().stream()
                .sorted(Comparator.comparingDouble(Coins::getPrice).reversed())
                .limit(number)
                .toList();
        for(Coins coin : topCoins) {
            logger.info(coin.getName());
        }
    }
    public void showTradersBasedOnProfit(TraderList traderList) {
        List<Traders> top5Traders = traderList.getTradersArray().stream()
                .sorted(Comparator.comparingDouble(Traders::getProfit).reversed())
                .limit(5)
                .toList();

        logger.info("Top 5 Traders based on Profit/Loss:");
        top5Traders.forEach(trader ->
                logger.info(trader.getFirstname()+" "+trader.getLastname()+" : "+ trader.getProfit())
        );

        List<Traders> bottom5Traders = traderList.getTradersArray().stream()
                .sorted(Comparator.comparingDouble(Traders::getProfit))
                .limit(5)
                .toList();

        logger.info("\nBottom 5 Traders based on Profit/Loss:");
        bottom5Traders.forEach(trader ->
                logger.info(trader.getFirstname()+" "+trader.getLastname()+" : "+ trader.getProfit()));
    }
}
