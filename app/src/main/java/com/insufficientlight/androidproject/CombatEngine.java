package com.insufficientlight.androidproject;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import static android.content.ContentValues.TAG;


public class CombatEngine
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

    public static void battleLoop(Army armyOne, Army armyTwo)
    {
        int armyOneUnits;
        int armyTwoUnits;
        int armyOnePower;
        int armyTwoPower;
        int counter = 0;
        int losses1;
        int losses2;
        boolean retreat = false;
        //GameActivity.Con.setVisibility(View.GONE);
        //GameActivity.Sur.setVisibility(View.GONE);
        //Method Calls
        armyOnePower = armyOne.getCombatPower();
        armyTwoPower = armyTwo.getCombatPower();
        armyOneUnits = armyOne.getTroopCount();
        armyTwoUnits = armyTwo.getTroopCount();

        //Main combat loop
        while (!retreat)
        {

            losses1 = armyTwoPower * (armyTwoUnits % 10 ) +  getRandomNumberInRange(1, 20);
            losses2 = armyOnePower * (armyOneUnits % 10 ) + getRandomNumberInRange(1, 20);

            GameActivity.Los1.append("\n" +"Round: " + (counter +1) + " " + armyOne.getArmyName() + " has lost " + losses1 + " soldiers!");
            GameActivity.Los2.append("\n" +"Round: " + (counter +1) + " " + armyTwo.getArmyName() + " has lost " + losses2 + " soldiers!");


            armyOneUnits = armyOneUnits - losses1;
            armyTwoUnits = armyTwoUnits - losses2;

            counter = counter + 1;

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

