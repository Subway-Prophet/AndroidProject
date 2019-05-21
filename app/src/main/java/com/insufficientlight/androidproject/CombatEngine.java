package com.insufficientlight.androidproject;
import android.app.AlertDialog;
import android.util.Log;

import java.util.Random;

class CombatEngine
    //Combat engine is the handler for combats, it calculates the math and spits the results back out to the user
{

    public static int attackerLosses;
    public static int attackerArcherLosses;
    public static int attackerCavLosses;
    public static int attackerSiegeLosses;
    public static int defenderLosses;
    public static int defenderArcherLosses;
    public static int defenderCavLosses;
    public static int defenderSiegeLosses;
    //Defender new army sizes
    public static int defenderNewCount;
    public static int defenderArcNewCount;
    public static int defenderCavNewCount;
    public static int defenderSiegeNewCount;
    public static int attackerNewCount;
    public static int attackerArcNewCount;
    public static int attackerCavNewCount;
    public static int attackerSiegeNewCount;

    //Generates a random number within the Provided range
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
        //Attacker losses

         attackerLosses = 0;
         attackerArcherLosses = 0;
         attackerCavLosses = 0;
         attackerSiegeLosses = 0;


        //Attacker new army sizes


        //Defender losses

         defenderLosses = 0;
         defenderArcherLosses = 0;
         defenderCavLosses = 0;
         defenderSiegeLosses = 0;





        int defenderInf = skirmish.getBattle().getDefender().getNumInf();
        int defenderArc = skirmish.getBattle().getDefender().getNumArc();
        int defenderCav = skirmish.getBattle().getDefender().getNumCav();
        int defenderSie = skirmish.getBattle().getDefender().getNumSie();

        int attackerInf = skirmish.getBattle().getAttacker().getNumInf();
        int attackerArc = skirmish.getBattle().getAttacker().getNumArc();
        int attackerCav = skirmish.getBattle().getAttacker().getNumCav();
        int attackerSie = skirmish.getBattle().getAttacker().getNumSie();

        if (defenderInf == 0)
        {
            //Attacker Wins
            return 1;
        }
        else if (attackerInf == 0)
        {
            //Defender Wins
            return 2;
        }
        else
        {
            attackerLosses = ((defenderInf / 50) * getRandomNumberInRange(2,3));
            defenderLosses = ((attackerInf / 50) * getRandomNumberInRange(2,3));

            attackerLosses = attackerLosses + (defenderArc / 50) * getRandomNumberInRange(2,4);
            defenderLosses = defenderLosses + (attackerArc / 50) * getRandomNumberInRange(2,4);

            Log.i("It ran an did not", "Noooo Halp defender " + defenderLosses);
            Log.i("It ran an did not", "Noooo Halp attacker " + attackerLosses);

            //Does some in between math because java hates me
            attackerNewCount = (skirmish.getBattle().getAttacker().getNumInf() - attackerLosses);
            attackerArcNewCount = (skirmish.getBattle().getAttacker().getNumArc() - attackerArcherLosses);
            attackerCavNewCount = (skirmish.getBattle().getAttacker().getNumCav() - attackerCavLosses);
            attackerSiegeNewCount = (skirmish.getBattle().getAttacker().getNumSie() - attackerSiegeLosses);

            //Does some in between math because java hates me
            defenderNewCount = (skirmish.getBattle().getDefender().getNumInf() - defenderLosses);
            defenderArcNewCount = (skirmish.getBattle().getDefender().getNumArc() - defenderArcherLosses);
            defenderCavNewCount = (skirmish.getBattle().getDefender().getNumCav() - defenderCavLosses);
            defenderSiegeNewCount = (skirmish.getBattle().getDefender().getNumSie() - defenderSiegeLosses);

            //Sets the attackers losses
            skirmish.getBattle().getAttacker().setNumInf(attackerNewCount);
            skirmish.getBattle().getAttacker().setNumArc(attackerArcNewCount);
            skirmish.getBattle().getAttacker().setNumCav(attackerCavNewCount);
            skirmish.getBattle().getAttacker().setNumSie(attackerSiegeNewCount);

            //Sets the Defender's losses
            skirmish.getBattle().getDefender().setNumInf(defenderNewCount);
            skirmish.getBattle().getDefender().setNumArc(defenderArcNewCount);
            skirmish.getBattle().getDefender().setNumCav(defenderCavNewCount);
            skirmish.getBattle().getDefender().setNumSie(defenderSiegeNewCount);

            Log.i("It ran an did not", "Noooo Halp");
            return 0;
        }
    }
   /**public static void infantryClash(Skirmish skirmish)
    {
        int attackerInf = skirmish.Attacker.getNumInf();
        int defenderInf = skirmish.Defender.getNumInf();
    }
    public static int defenderArcherFire(Skirmish skirmish)
    {
        return 1;
    }
    public static int attackerArcherFire(Skirmish skirmish)
    {
        return 1;
    }
    public static int defenderCavAction(Skirmish skirmish)
    {
        return 1;
    }
    public static int attackerCavAction(Skirmish skirmish)
    {
        return 1;
    }**/


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


