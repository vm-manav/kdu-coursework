package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IplList {
    private ArrayList<IplData> allIplDataList=new ArrayList<>();


    public void addData(IplData data){
        allIplDataList.add(data);
    }
    public ArrayList<IplData> getListData() {
        return allIplDataList;
    }

    public ArrayList<String> getBowlers(String teamName) {
        ArrayList<String> list=new ArrayList<>();
        for(IplData data:allIplDataList) {
            if(data.getTeamName().equalsIgnoreCase(teamName) && data.getWickets()>=40 &&
                    (data.getRole().equalsIgnoreCase("ALL ROUNDER")||data.getRole().equalsIgnoreCase("BOWLER"))) {

                list.add(data.getPlayerName());
            }
        }
        return list;
    }
    public IplData getHighestWicketTaker(String teamName) {
        int wickets=-1;
        IplData currentPlayer=null;
        for(IplData newData:allIplDataList) {
            newData.print();
            if(newData.getTeamName().equalsIgnoreCase(teamName) && (newData.getWickets()>wickets || currentPlayer==null)) {
                    wickets=newData.getWickets();
                    currentPlayer=newData;

            }
        }
        return currentPlayer;
    }
    public IplData getHighestScorer(String teamName) {
        int score=-1;
        IplData currentPlayer=null;
        for(IplData newData:allIplDataList) {
            if(newData.getTeamName().equalsIgnoreCase(teamName) && (newData.getRuns()>score || currentPlayer==null)) {
                score=newData.getWickets();
                currentPlayer=newData;

            }
        }
        return currentPlayer;
    }
    public List<IplData> topWicketScorerSeason() {
        List<IplData> list=  allIplDataList.stream()
                .sorted(Comparator.comparingInt(IplData::getWickets).reversed())
                .limit(3)
                .toList();
        return list;
    }
    public List<IplData> topScorerSeason() {
        List<IplData> list=  allIplDataList.stream()
                .sorted(Comparator.comparingInt(IplData::getRuns).reversed())
                .limit(3)
                .toList();
        return list;
    }
}

