package com.insufficientlight.androidproject;

public class StandardSkirmish extends Skirmish
{
    //Basic Skirmish
    Army Attacker = null;
    Army Defender = null;

    public StandardSkirmish(Army Defender, Army Attacker)
    {
       this.Defender = Defender;
       this.Attacker = Attacker;
    }




}
