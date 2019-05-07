package com.insufficientlight.androidproject;

import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class CombatEngine
    //Combat engine is the handler for combats, it calculates the math and spits the results back out to the user
{

    public static int armyLosses (int armyOneCP, int armyTwoCP, int armyOneTroops, int armyTwoTroops)
    {
        //Slight Progress
        double kills1 = 0;
        kills1 = (armyOneCP * (armyOneTroops * .01));
        int result = (int) kills1;
        return result;
    }

    public static int combatPowerCalculate (Army armyName)
    {
        //Calculates the Damage each side can afflict
        return 100;
    }

    public static void battleLoop(Army armyOne, Army armyTwo)
    {

        int armyOneUnits = 0;
        int armyTwoUnits = 0;
        int armyOnePower = 0;
        int armyTwoPower = 0;
        int counter = 0;
        int losses1 = 0;
        int losses2 = 0;
        boolean retreat = false;



        //Method Calls
        armyOnePower = combatPowerCalculate(armyOne);
        armyTwoPower = combatPowerCalculate(armyTwo);
        armyOneUnits = armyOne.getTroopCount();
        armyTwoUnits = armyTwo.getTroopCount();

        //Main combat loop
        while (!retreat)
        {
            losses1 = 0;
            losses2 = 0;
            if (counter >= 2)
            {
                //Offer Retreat
                try
                {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
                    losses1 = armyLosses(armyOnePower, armyTwoPower, armyOneUnits, armyTwoUnits);
                    losses2 = armyLosses(armyOnePower, armyTwoPower, armyOneUnits, armyTwoUnits);

                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
            GameActivity.Los1.append("\n" + armyOne.getArmyName() + " has lost " + losses1 + " soldiers!");
            GameActivity.Los2.append("\n" + armyTwo.getArmyName() + " has lost " + losses2 + " soldiers!");

            armyOneUnits = armyOneUnits - losses1;
            armyTwoUnits = armyTwoUnits - losses2;

            if (armyOneUnits == 0)
            {
                retreat = true;
            }
            if (armyTwoUnits == 0)
            {
                retreat = true;
            }
        }

    }

}

