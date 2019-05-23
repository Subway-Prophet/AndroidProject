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
            //yeet
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


        // Fail safes for empty ranks
        if (defenderInf > 0)
        {
            attackerLosses = ((defenderInf / 50) * getRandomNumberInRange(2, 3));
            if ((attackerCav > 0) && (defenderInf > attackerInf) && (defenderCav == 0))
            {
                attackerCavLosses = (defenderInf /100) * getRandomNumberInRange(1, 5);
            }
        }
        if (defenderArc > 0)
        {
            attackerLosses = attackerLosses + (defenderArc / 50) * getRandomNumberInRange(2, 4);
        }
        if (defenderCav > 0)
        {
            if (attackerCav == 0 && attackerArc != 0)
            {
                attackerArcherLosses = attackerArcherLosses + (defenderCav / 40) * getRandomNumberInRange(2, 5);
            }
            else if (attackerCav != 0)
            {
                attackerCavLosses = attackerCavLosses + (defenderCav / 40) * getRandomNumberInRange(2, 5);
            }
            else if (defenderSie > 0)
            {
                attackerSiegeLosses = attackerSiegeLosses + (defenderCav / 40) * getRandomNumberInRange(2, 5);
            }
            else
            {
                attackerLosses = attackerLosses + ((defenderCav / 40) * getRandomNumberInRange(2, 5));
            }

        }
        if (defenderSie > 0)
        {
            attackerLosses = attackerLosses + (defenderSie) * getRandomNumberInRange(2, 6);
        }

        // Fail safes for empty ranks
        if (attackerInf > 0)
        {
            defenderLosses = ((attackerInf / 50) * getRandomNumberInRange(2, 3));
            if ((defenderCav > 0) && (attackerInf > defenderInf) && (attackerCav == 0))
            {
                attackerCavLosses = (attackerInf /100) * getRandomNumberInRange(1, 5);
            }
        }
        if (attackerArc > 0)
        {
            defenderLosses = defenderLosses + (attackerArc / 50) * getRandomNumberInRange(2, 4);
        }
        if (attackerCav > 0)
        {
            if (defenderCav == 0 && defenderArc != 0)
            {
                defenderArcherLosses = defenderArcherLosses + (attackerCav / 40) * getRandomNumberInRange(2, 5);
            }
            else if (defenderCav != 0)
            {
                defenderCavLosses = defenderCavLosses + (attackerCav / 40) * getRandomNumberInRange(2, 5);
            }
            else if (defenderSie > 0)
            {
                defenderSiegeLosses = defenderSiegeLosses + (attackerCav / 40) * getRandomNumberInRange(2, 5);
            }
            else
            {
                defenderLosses = defenderLosses + ((attackerCav / 40) * getRandomNumberInRange(2, 5));
            }
        }
        if (attackerSie > 0)
        {
            defenderLosses = defenderLosses + (attackerSie) * getRandomNumberInRange(2, 6);
        }


        // Fail Safes for depleted ranks
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
            defenderCavNewCount = (skirmish.getBattle().getDefender().getNumCav() - defenderCavLosses);
        }
        if (defenderSie - defenderSiegeLosses <= 0)
        {
            defenderSiegeNewCount = 0;
        }
        else
        {
            defenderSiegeNewCount = (skirmish.getBattle().getDefender().getNumSie() - defenderSiegeLosses);
            //oof
        }

        // Fail Safes for depleted ranks
        if (attackerInf - attackerLosses <= 0)
        {
            attackerNewCount = 0;
        }
        else
        {
            attackerNewCount = (skirmish.getBattle().getAttacker().getNumInf() - attackerLosses);
        }
        if (attackerArc - attackerArcherLosses <= 0)
        {
            attackerArcNewCount = 0;
        }
        else
        {
            attackerArcNewCount = (skirmish.getBattle().getAttacker().getNumArc() - attackerArcherLosses);
        }
        if (attackerCav - attackerCavLosses <= 0)
        {
            attackerCavNewCount = 0;
        }
        else
        {
            attackerCavNewCount = (skirmish.getBattle().getAttacker().getNumCav() - attackerCavLosses);
        }
        if (attackerSie - attackerSiegeLosses <= 0)
        {
            attackerSiegeNewCount = 0;
        }
        else
        {
            attackerSiegeNewCount = (skirmish.getBattle().getAttacker().getNumSie() - attackerSiegeLosses);
        }




        Log.i("It ran an did not", "Noooo Halp defender " + defenderLosses);
            Log.i("It ran an did not", "Noooo Halp attacker " + attackerLosses);

            //Does some in between math because java hates me
            //attackerNewCount = (skirmish.getBattle().getAttacker().getNumInf() - attackerLosses);
            //attackerArcNewCount = (skirmish.getBattle().getAttacker().getNumArc() - attackerArcherLosses);
            //attackerCavNewCount = (skirmish.getBattle().getAttacker().getNumCav() - attackerCavLosses);
            //attackerSiegeNewCount = (skirmish.getBattle().getAttacker().getNumSie() - attackerSiegeLosses);

            //Does some in between math because java hates me
            //defenderNewCount = (skirmish.getBattle().getDefender().getNumInf() - defenderLosses);
            //defenderArcNewCount = (skirmish.getBattle().getDefender().getNumArc() - defenderArcherLosses);
            //defenderCavNewCount = (skirmish.getBattle().getDefender().getNumCav() - defenderCavLosses);
            //defenderSiegeNewCount = (skirmish.getBattle().getDefender().getNumSie() - defenderSiegeLosses);

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