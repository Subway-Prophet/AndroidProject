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
    public static void calculateLosses(StandardSkirmish skirmish)
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

        /**if ((skirmish.battle.attacker.getNumInf()+ skirmish.battle.attacker.getNumCav() + skirmish.battle.attacker.getNumArc() + skirmish.battle.attacker.getNumSie()) == 0)
        {
            return skirmish.battle.defender.getPlayerTag();
        }**/
        /**if ((skirmish.battle.defender.getNumInf() + skirmish.battle.defender.getNumCav() + skirmish.battle.defender.getNumArc() + skirmish.battle.defender.getNumSie() == 0))
        {
            return skirmish.battle.attacker.getPlayerTag();
        }**/
        /**if (skirmish.getPlayer1Tactics().equals("Phalanx") && !("Phalanx").equals(skirmish.getPlayer2Tactics()))
        {
            P1TA = true;
        }
        if (skirmish.getPlayer1Tactics().equals("Phalanx") && !("Phalanx").equals(skirmish.getPlayer1Tactics()))
        {
            P2TA = true;
        }
        if (skirmish.getPlayer1Archery().equals("Full Volleys") && !("Turtle Formation").equals(skirmish.getPlayer2Tactics()))
        {
            P1AA = true;
        }
        if (skirmish.getPlayer2Archery().equals("Full Volleys") && !("Turtle Formation").equals(skirmish.getPlayer1Tactics()))
        {
            P2AA = true;
        }
        if (skirmish.battle.getTerrain().isCavFavored())
        {
            P1CA = true;
            P2CA = true;
        }
        if (skirmish.battle.getTerrain().isSeigeWeaponFavored())
        {
            P1SA = true;
        }**/
        //The one is a place holder for the unit's combat bonus
        skirmish.getBattle().getAttacker().setNumInf(111);
        skirmish.getBattle().getDefender().setNumInf(250);
        Log.i("It ran an did not", "Noooo Halp");

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


