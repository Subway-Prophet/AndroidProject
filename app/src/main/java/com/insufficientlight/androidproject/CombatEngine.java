package com.insufficientlight.androidproject;

import android.widget.Button;
import android.widget.TextView;


public class CombatEngine
    //Combat engine is the handler for combats, it calculates the math and spits the results back out to the user
{
    /**
    //Surrender Button
    public static Button Sur;
    //Keep fighting Button
    public static Button Con;
    //Losses text View for side one
    public static TextView Los1;
    //Losses text View for side two
    public static TextView Los2;
    //Army1's army name text view
    public static TextView An1;
    //Army2's army name text view
    public static TextView An2;
     **/

    public static int armyLosses (int armyOneCP, int armyTwoCP, int armyOneTroops, int armyTwoTroops)
    {
        //Calculates the losses on one side.
        return 100;
    }

    public static int combatPowerCalculate (Army armyName)
    {
        //Calculates the Damage each side can afflict
        return 100;
    }

    public static void battleLoop(Army armyOne, Army armyTwo)
    {
        /**Sur.findViewById(R.id.Surrender);
        Con.findViewById(R.id.KeepFighting);
        Los1.findViewById(R.id.ArmyOneLoss);
        Los2.findViewById(R.id.ArmyTwoLoss);
        An1.findViewById(R.id.ArmyOne);
        An2.findViewById(R.id.ArmyTwo);**/
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

