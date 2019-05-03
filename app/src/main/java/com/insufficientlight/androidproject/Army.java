package com.insufficientlight.androidproject;

public class Army
{
    String armyName;
    int troopMorale;
    int troopCount;
    int troopWeaponQ;
    public String getArmyName()
    {
        return armyName;
    }

    public void setArmyName(String armyName)
    {
        this.armyName = armyName;
    }
    public void setTroopCount(int troopCount)
    {
        this.troopCount = troopCount;
    }

    public int getTroopCount()
    {
        return troopCount;
    }

    public int getTroopWeaponQ()
    {
        return troopWeaponQ;
    }

    public void setTroopWeaponQ(int troopWeaponQ)
    {
        this.troopWeaponQ = troopWeaponQ;
    }
    public int getTroopMorale()
    {
        return troopMorale;
    }
    public void setTroopMorale(int troopMorale)
    {
        this.troopMorale = troopMorale;
    }
    public Army(String armyName, int troopWeaponQ, int troopMorale, int troopCount)
    {
            this.armyName = armyName;
            this.troopCount = troopCount;
            this.troopWeaponQ = troopWeaponQ;
            this.troopMorale = troopMorale;
    }

}
