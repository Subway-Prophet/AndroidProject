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
}

