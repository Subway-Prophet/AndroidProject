package com.insufficientlight.androidproject;

public class Army
{
    String armyName;
    int troopCount;
    int combatPower;
    int numInf;
    int numCav;
    int numArc;
    int numSie;
    int playerTag;

    public int getCombatPower()
    {
        return combatPower;
    }
    public void setCombatPower(int combatPower)
    {
        this.combatPower = combatPower;
    }
    public String getArmyName()
    {
        return armyName;
    }
    public void setArmyName(String armyName)
    {
        this.armyName = armyName;
    }
    public int getNumInf()
    {
        return numInf;
    }
    public void setNumInf(int numInf)
    {
        this.numInf = numInf;
    }
    public int getNumCav()
    {
        return numCav;
    }
    public int getNumArc()
    {
        return numArc;
    }
    public void setNumCav(int numCav)
    {
        this.numCav = numCav;
    }
    public void setNumArc(int numArc)
    {
        this.numArc = numArc;
    }
    public int getNumSie()
    {
        return numSie;
    }
    public void setNumSie(int numSie)
    {
        this.numSie = numSie;
    }

    public int getPlayerTag()
    {
        return playerTag;
    }

    public void setPlayerTag(int playerTag)
    {
        this.playerTag = playerTag;
    }

    public Army(String armyName, int playerTag, int numInf, int numArc, int numCav, int numSie)
    {
        this.armyName = armyName;

        this.numInf = numInf;
        this.numCav = numCav;
        this.numArc = numArc;
        this.numSie = numSie;
        this.playerTag = playerTag;
    }

}
