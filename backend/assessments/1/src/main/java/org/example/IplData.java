package org.example;

public class IplData {
    private String playerName;
    private String teamName;
    private String role;
    private int runs;
    private int matches;
    private double average;
    private double srValue;
    private int wickets;

    public IplData(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
        this.teamName=s;
        this.playerName=s1;
        this.role=s2;
        this.matches=Integer.parseInt(s3);
        this.runs=Integer.parseInt(s4);
        this.average=Double.parseDouble(s5);
        this.srValue=Double.parseDouble(s6);
        this.wickets=Integer.parseInt(s7);
    }


    public String getPlayerName() {
        return playerName;
    }

    public double getAverage() {
        return average;
    }

    public int getRuns() {
        return runs;
    }

    public double getSrValue() {
        return srValue;
    }

    public int getWickets() {
        return wickets;
    }

    public int getMatches() {
        return matches;
    }

    public String getRole() {
        return role;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public void setSrValue(double srValue) {
        this.srValue = srValue;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public void print(){
        System.out.println(getPlayerName()+" , "+getTeamName()+" , "+getRole()+" , "+getMatches()+" , "+getRuns()+" , "+getAverage()+" , "+getSrValue()+" , "+getWickets());
    }
}
