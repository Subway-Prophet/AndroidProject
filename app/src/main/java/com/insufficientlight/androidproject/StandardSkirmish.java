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



    public StandardSkirmish(int SkirmID, int player1, int player2, Battle battle, String player1Tactics, String player1Cavalry, String player1Archery, String player2Tactics, String player2Cavalry, String player2Archery)
    {
        this.SkirmID = SkirmID;
        this.player1 = player1;
        this.player2 = player2;
        this.battle = battle;
        this.player1Tactics = player1Tactics;
        this.player1Archery = player1Archery;
        this.player1Cavalry = player1Cavalry;
        this.player2Tactics = player2Tactics;
        this.player2Archery = player2Archery;
        this.player2Cavalry = player2Cavalry;

    }


}
