package com.insufficientlight.androidproject;

public class StandardSkirmish extends Skirmish
{
    //Basic Skirmish
    Army Attacker = null;
    Army Defender = null;
    String P1Form = null;
    String P2Form = null;
    String P1Cav = null;
    String P2Cav = null;
    String P1Tac = null;
    String P2Tac = null;



    public StandardSkirmish(Army Defender, Army Attacker, String P1Form, String P2Form, String P1Cav, String P2Cav, String P1Tac, String P2Tac)
    {
       this.Defender = Defender;
       this.Attacker = Attacker;
       this.P1Form = P1Form;
       this.P2Form = P2Form;
       this.P1Cav = P1Cav;
       this.P2Cav = P2Cav;
       this.P1Tac = P1Tac;
       this.P2Tac = P2Tac;
    }




}
