package com.insufficientlight.androidproject;

public class Unit
{
    //The unit types name
    String name;
    //The Speed modifier of the unit
    int speed;
    //The power of the unit type
    int combatBonusMultiplier;
    //Can the unit type attack from behind lines?
    Boolean fireFromBack;
    public Unit(String name, int speed, int combatBonusMultiplier, boolean fireFromBack)
    {
        this.name = name;
        this.speed = speed;
        this.combatBonusMultiplier = combatBonusMultiplier;
        this.fireFromBack = fireFromBack;
    }
}