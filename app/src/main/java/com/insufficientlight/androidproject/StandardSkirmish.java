package com.insufficientlight.androidproject;

public class StandardSkirmish extends Skirmish
{
   int player1 = 0;
   int player2 = 0;
   Battle battle = null;

    public StandardSkirmish(int player1, int player2, Battle battle)
    {
        this.player1 = player1;
        this.player2 = player2;
        this.battle = battle;
    }


}
