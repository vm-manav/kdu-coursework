package org.example;

import org.example.coin.Coins;
import org.example.coin.CoinsList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.trader.TraderList;
import org.example.trader.Traders;
import org.example.transaction.Transactions;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static final CoinsList coinsList=new CoinsList();
    public static final TraderList traderList=new TraderList();
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        // This main method is intentionally left empty.
        // It serves as an entry point for the application.
        // Add relevant code here based on the application requirements.
    }
    public static ArrayList<String[]> parseCSV(Path path) throws IOException {
        ArrayList<String[]> data = new ArrayList<>();
        try(BufferedReader reader=new BufferedReader(new FileReader(path.toString()))){
            String readLine=reader.readLine();
            while ((readLine = reader.readLine()) != null) {
                String[] columns = readLine.split(",");
                if(columns.length==6) {
                    Coins coin=new Coins(columns[0],columns[1],columns[2],columns[3],columns[4],columns[5]);
                    coinsList.addCoin(coin);
                } else if (columns.length==5) {
                    Traders trader=new Traders(columns[0],columns[1],columns[2],columns[3],columns[4]);
                    traderList.addTrader(trader);
                }
                data.add(columns);
                return data;
            }
        }catch (IOException I) {
            logger.log(Level.SEVERE, "Error reading the file", I);
        }

        return new ArrayList<>();
    }
    public static JsonNode parseJsonFile(String path) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File(path);
            JsonNode jsonNode=objectMapper.readTree(file);
            executeTransactions(jsonNode, new CountDownLatch(3));

        } catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, "Error reading the file", e);
        }
        return null;
    }
    public static void executeTransactions(JsonNode jsonTransactions, CountDownLatch latch) throws JsonProcessingException, InterruptedException {
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        ObjectMapper objectMapper = new ObjectMapper();
        Transactions[] dataList = objectMapper.treeToValue(jsonTransactions, Transactions[].class);
        for (Transactions transaction : dataList) {
            ExecuteTransaction executeTransaction=new ExecuteTransaction(transaction,coinsList,traderList, latch);
            executorService.submit(executeTransaction);
        }
    }
}
