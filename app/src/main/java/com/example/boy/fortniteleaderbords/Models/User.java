package com.example.boy.fortniteleaderbords.Models;

/**
 * Created by Boy on 10/25/2017.
 */

public class User {
    private String userName;
    private int soloKills;
    private int soloGames;
    private int soloWins;
    private int duoKills;
    private int duoGames;
    private int duoWins;
    private int sqaudKills;
    private int sqaudGames;
    private int sqaudWins;

    public User(String userName){
        this.userName =userName;
    }
    public User(String userName,int soloKills,int soloGames,int soloWins,int duoKills,int duoGames,int duoWins,int sqaudKills,int sqaudGames,int sqaudWins){
        this.userName=userName;
        this.soloKills=soloKills;
        this.soloGames=soloGames;
        this.soloWins=soloWins;
        this.duoKills=duoKills;
        this.duoGames=duoGames;
        this.duoWins=duoWins;
        this.sqaudGames=sqaudGames;
        this.sqaudKills=sqaudKills;
        this.sqaudWins=sqaudWins;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSoloKills() {
        return soloKills;
    }

    public void setSoloKills(int soloKills) {
        this.soloKills = soloKills;
    }

    public int getSoloGames() {
        return soloGames;
    }

    public void setSoloGames(int soloGames) {
        this.soloGames = soloGames;
    }

    public int getSoloWins() {
        return soloWins;
    }

    public void setSoloWins(int soloWins) {
        this.soloWins = soloWins;
    }

    public int getDuoKills() {
        return duoKills;
    }

    public void setDuoKills(int duoKills) {
        this.duoKills = duoKills;
    }

    public int getDuoGames() {
        return duoGames;
    }

    public void setDuoGames(int duoGames) {
        this.duoGames = duoGames;
    }

    public int getDuoWins() {
        return duoWins;
    }

    public void setDuoWins(int duoWins) {
        this.duoWins = duoWins;
    }

    public int getSqaudKills() {
        return sqaudKills;
    }

    public void setSqaudKills(int sqaudKills) {
        this.sqaudKills = sqaudKills;
    }

    public int getSqaudGames() {
        return sqaudGames;
    }

    public void setSqaudGames(int sqaudGames) {
        this.sqaudGames = sqaudGames;
    }

    public int getSqaudWins() {
        return sqaudWins;
    }

    public void setSqaudWins(int sqaudWins) {
        this.sqaudWins = sqaudWins;
    }
}
