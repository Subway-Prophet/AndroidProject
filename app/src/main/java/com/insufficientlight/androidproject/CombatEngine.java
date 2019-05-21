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
    public static void calculateLosses(StandardSkirmish skirmish)
    {
        //Attacker losses

        attackerLosses = 0;
        attackerArcherLosses = 0;
        attackerCavLosses = 0;
        attackerSiegeLosses = 0;


        //Attacker new army sizes
        int attackerNewCount;
        int attackerArcNewCount;
        int attackerCavNewCount;
        int attackerSiegeNewCount;

        //Defender losses

        defenderLosses = 0;
        defenderArcherLosses = 0;
        defenderCavLosses = 0;
        defenderSiegeLosses = 0;


        //Defender new army sizes
        int defenderNewCount;
        int defenderArcNewCount;
        int defenderCavNewCount;
        int defenderSiegeNewCount;


        int defenderInf = skirmish.getBattle().getDefender().getNumInf();
        int defenderArc = skirmish.getBattle().getDefender().getNumArc();
        int defenderCav = skirmish.getBattle().getDefender().getNumCav();
        int defenderSie = skirmish.getBattle().getDefender().getNumSie();

        int attackerInf = skirmish.getBattle().getAttacker().getNumInf();
        int attackerArc = skirmish.getBattle().getAttacker().getNumArc();
        int attackerCav = skirmish.getBattle().getAttacker().getNumCav();
        int attackerSie = skirmish.getBattle().getAttacker().getNumSie();

        attackerLosses = ((defenderInf / 50) * getRandomNumberInRange(2, 3));

        attackerLosses = attackerLosses + (defenderArc / 50) * getRandomNumberInRange(2, 4);

        attackerArcherLosses = (defenderCav / 50) * getRandomNumberInRange(2, 5);

        defenderLosses = ((attackerInf / 50) * getRandomNumberInRange(2, 3));

        defenderLosses = defenderLosses + (attackerArc / 50) * getRandomNumberInRange(2, 4);

        defenderArcherLosses = (attackerCav / 50) * getRandomNumberInRange(2, 5);

        if (defenderInf - defenderLosses <= 0)
        {
            defenderNewCount = 0;
        }
        else
        {
            defenderNewCount = (skirmish.getBattle().getDefender().getNumInf() - defenderLosses);
        }
        if (defenderArc - defenderArcherLosses <= 0)
        {
            defenderArcNewCount = 0;
        }
        else
        {
            defenderArcNewCount = (skirmish.getBattle().getDefender().getNumArc() - defenderArcherLosses);
        }
        if (defenderCav- defenderCavLosses <= 0)
        {
            defenderCavNewCount = 0;
        }
        else
        {
            defenderCavNewCount = (skirmish.getBattle().getDefender().getNumArc() - defenderArcherLosses);
        }


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
    }
}



