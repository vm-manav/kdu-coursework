package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static IplList allDataList=new IplList();
    public static void main(String[] args) {

        String filePath="src/main/resources/IPL_2021-data.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] container = line.split(",");
                //System.out.println(container[0]+container[1]);
                IplData data=new IplData(container[0],container[1],container[2],container[3],container[4],container[5],container[6],container[7]);
                allDataList.addData(data);
//                data.print();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        int choice=1;
        while (choice==1) {
            Scanner sc=new Scanner(System.in);
            System.out.println("1.Get bowlers with >40 wickets \n2.Get highest wicket taker and highest run scorer \n3.get top 3 wicket taker and top 3 run scorer\n");
            System.out.println(" Choose anyone options : ");
            int option=sc.nextInt();
            if(option==1) {
                System.out.println("Enter Team name :");
                String teamName=sc.next();
                ArrayList<String> list=allDataList.getBowlers(teamName);
                if(list.isEmpty()) {
                    System.out.println("no such bowler found with atleat 40 wickets in team "+teamName);
                } else {
                    for(String name:list) {
                        System.out.println(name);
                    }
                }
            } else if (option==2) {
                System.out.println("Enter Team name :");
                String teamName=sc.next();
                IplData highestWicketScorer=allDataList.getHighestWicketTaker(teamName);
                IplData highestScorer=allDataList.getHighestWicketTaker(teamName);

                if(highestWicketScorer!=null) {
                    highestWicketScorer.print();
                } else {
                    System.out.println("no data");
                }
                if(highestScorer!=null) {
                    highestScorer.print();
                } else {
                    System.out.println("no data");
                }
            } else if (option==3) {
                List<IplData> newData=allDataList.topWicketScorerSeason();
                for(IplData str:newData) {
                    str.print();
                }
                List<IplData> newData2=allDataList.topScorerSeason();
                for(IplData str:newData2) {
                    str.print();
                }
            } else  {
                System.out.println("Invalid option ");
            }
            System.out.println("do you wish to continue 1:yes 0:no");
            choice= sc.nextInt();
        }


    }
}