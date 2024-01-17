package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.coin.Coins;
import org.example.coin.CoinsList;

import org.example.datafetch.HelperFunctions;
import org.example.trader.TraderList;
import org.example.trader.Traders;
import org.example.transaction.Transactions;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static final CoinsList coinsList=new CoinsList();
    public static final TraderList traderList=new TraderList();
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonTransactions = mapper.readTree(new File(Constants.FILE_JSON_PATH));
            parseCSV(Path.of(Constants.COIN_CSV_PATH));
            parseCSV(Path.of(Constants.TRADER_CSV_PATH));
            int transactionCount = jsonTransactions.size();
            CountDownLatch latch = new CountDownLatch(transactionCount);
            executeTransactions(jsonTransactions, latch);
            latch.await();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }

        HelperFunctions helperFunctions=new HelperFunctions();
            helperFunctions.detailsOfCoin("ETH",coinsList);
            helperFunctions.showTopCoins(coinsList,8);
            helperFunctions.showTraderPortfolio("0xaf903c532c73b66c934f6e2356344bb0",traderList);
            helperFunctions.showTradersBasedOnProfit(traderList);
        helperFunctions.showTraderProfit("0x1397ffcfbd2badb81a0734035a957ef1",traderList);
    }
    public static ArrayList<String[]> parseCSV(Path path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
            ArrayList<String[]> data = new ArrayList<>();

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                if(columns.length == 6) {
                    Coins coin=new Coins(columns[0],columns[1],columns[2],columns[3],columns[4],columns[5]);
                    coinsList.addCoin(coin);
                } else if (columns.length == 5) {
                    Traders trader=new Traders(columns[0],columns[1],columns[2],columns[3],columns[4]);
                    TraderList.tradersArray.add(trader);

                }

                data.add(columns);
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
    public static JsonNode parseJsonFile(String path) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readTree(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void executeTransactions(JsonNode jsonTransactions, CountDownLatch
            latch) throws JsonProcessingException, InterruptedException {
        ExecutorService execService = Executors.newFixedThreadPool(10);

        ObjectMapper objectMapper = new ObjectMapper();
        Transactions[] transactions = objectMapper.treeToValue(jsonTransactions, Transactions[].class);

        ArrayList<Transactions> transactionArrayList = new ArrayList<>();
        Collections.addAll(transactionArrayList, transactions);

        for(Transactions obj : transactionArrayList) {
            execService.execute(new ExecuteTransaction(obj,coinsList,traderList));
            latch.countDown();
        }

        execService.shutdown();
    }
}