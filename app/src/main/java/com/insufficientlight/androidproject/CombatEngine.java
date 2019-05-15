package com.insufficientlight.androidproject;
import android.app.AlertDialog;
import android.util.Log;

import java.util.Random;

class CombatEngine
    //Combat engine is the handler for combats, it calculates the math and spits the results back out to the user
{
    private static int getRandomNumberInRange(int min, int max)
    {

        if (min >= max)
        {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    public static int calculateLosses(StandardSkirmish skirmish)
    {
        Boolean P1TA = false;
        Boolean P1AA = false;
        Boolean P1CA = false;
        Boolean P1SA = false;

        Boolean P2TA = false;
        Boolean P2AA = false;
        Boolean P2CA = false;
        Boolean P2SA = false;

        int P1FLL = 0;
        int P2FLL = 0;

        if ((skirmish.battle.attacker.numInf + skirmish.battle.attacker.numCav + skirmish.battle.attacker.numArc + skirmish.battle.attacker.numSie) == 0)
        {
            return skirmish.battle.defender.playerTag;
        }
        if ((skirmish.battle.defender.numInf + skirmish.battle.defender.numCav + skirmish.battle.defender.numArc + skirmish.battle.defender.numSie) == 0)
        {
            return skirmish.battle.attacker.playerTag;
        }
        if (skirmish.player1Tactics.equals("Phalanx") && ("Phalanx").equals(skirmish.player2Tactics) == false)
        {
            P1TA = true;
        }
        if (skirmish.player2Tactics.equals("Phalanx") && ("Phalanx").equals(skirmish.player1Tactics) == false)
        {
            P2TA = true;
        }
        if (skirmish.player1Archery.equals("Full Volleys") && ("Turtle Formation").equals(skirmish.player2Tactics) == false)
        {
            P1AA = true;
        }
        if (skirmish.player2Archery.equals("Full Volleys") && ("Turtle Formation").equals(skirmish.player1Tactics) == false)
        {
            P2AA = true;
        }
        if (skirmish.battle.getTerrain().cavFavored)
        {
            P1CA = true;
            P2CA = true;
        }
        if (skirmish.battle.getTerrain().seigeWeaponFavored)
        {
            P1SA = true;
        }
        //The one is a place holder for the unit's combat bonus
        skirmish.battle.defender.setNumInf(skirmish.battle.defender.numInf - ((skirmish.battle.attacker.getNumInf() % 10) * (1)) + ((skirmish.battle.attacker.getNumArc() % 10) * (1)));
        skirmish.battle.attacker.setNumInf(skirmish.battle.attacker.numInf - ((skirmish.battle.defender.getNumInf() % 10) * (1)) + ((skirmish.battle.defender.getNumArc() % 10) * (1)));
        return 0;

    }


}



    /**static void battleLoop(Army armyOne, Army armyTwo)
    {
        int counter = 0;
        boolean retreat = false;
        String output1;
        String output2;
        //GameActivity.Con.setVisibility(View.GONE);
        //GameActivity.Sur.setVisibility(View.GONE);

        //Main combat loop
        while (!retreat)
        {
            //Losses Calculation, Possible Culprit of memory leak
            armyOne.setTroopCount(((armyOne.getTroopCount()) - ((armyTwo.getCombatPower()) * ((armyTwo.getTroopCount()) % (getRandomNumberInRange(15, 20))))));
            armyTwo.setTroopCount(((armyTwo.getTroopCount()) -((armyOne.getCombatPower()) * ((armyOne.getTroopCount()) % (getRandomNumberInRange(15, 20))))));
            //Log.i()
            //Updates the user on losses
            //GameActivity.Los1.setText(armyOne.getArmyName() + "Now has  " + armyOne.getTroopCount() + " soldiers!");
            //GameActivity.Los2.setText(armyOne.getArmyName() + "Now has  " + armyOne.getTroopCount() + " soldiers!");
            //Ticking Counter
            counter = counter + 1;
            //Win/loss Conditions
            if (armyOne.getTroopCount() == 0)
            {
                retreat = true;
            }
            if (armyTwo.getTroopCount() == 0)
            {
                retreat = true;
            }
        }
    }**/


