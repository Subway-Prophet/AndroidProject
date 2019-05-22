package com.insufficientlight.androidproject;

public class StandardSkirmish extends Skirmish
{
    int SkirmID;
   int player1 = 0;
   int player2 = 0;
   Battle battle = null;
   String player1Tactics;
   String player1Cavalry;
   String player1Archery;
   String player2Tactics;
   String player2Cavalry;
   String player2Archery;


    public int getSkirmID()
    {
        return SkirmID;
    }

    public int getPlayer1()
    {
        return player1;
    }

    public int getPlayer2()
    {
        return player2;
    }

    public Battle getBattle()
    {
        return battle;
    }

    public String getPlayer1Tactics()
    {
        return player1Tactics;
    }

    public String getPlayer1Cavalry()
    {
        return player1Cavalry;
    }

    public String getPlayer1Archery()
    {
        return player1Archery;
    }

    public String getPlayer2Tactics()
    {
        return player2Tactics;
    }

    public String getPlayer2Cavalry()
    {
        return player2Cavalry;
    }

    public String getPlayer2Archery()
    {
        return player2Archery;
    }

    public StandardSkirmish(int SkirmID, int player1, int player2, Battle battle, String player1Tactics, String player1Cavalry, String player2Tactics, String player2Cavalry)
    {
        this.SkirmID = SkirmID;
        this.player1 = player1;
        this.player2 = player2;
        this.battle = battle;
        this.player1Tactics = player1Tactics;
        this.player1Cavalry = player1Cavalry;
        this.player2Tactics = player2Tactics;
        this.player2Cavalry = player2Cavalry;

    }


}
