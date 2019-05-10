package com.insufficientlight.androidproject;

public class Terrain
{
    //The name of the terrain
    String terrainType;
    //What Modifier does the terrain have on the number of troops field-able
    int frontLineCount;
    //The Bonus to retreat
    int retreatBonus;
    //The Bonus to Defenders
    int defenderBonus;
    //The Bonus to Attackers
    int attackerBonus;
    //Are cavalry favored?
    boolean cavFavored;
    //Are infantry favored?
    boolean infantryFavored;
    //Are Siege Weapons Favored?
    boolean seigeWeaponFavored;
    public Terrain(String terrainType, int frontLineCount, int retreatBonus, int attackerBonus, int defenderBonus
                    , boolean cavFavored, boolean infantryFavored, boolean seigeWeaponFavored)
    {
        this.seigeWeaponFavored = seigeWeaponFavored;
        this.terrainType = terrainType;
        this.frontLineCount = frontLineCount;
        this.retreatBonus = retreatBonus;
        this.attackerBonus = attackerBonus;
        this.cavFavored = cavFavored;
        this.infantryFavored = infantryFavored;
        this.defenderBonus = defenderBonus;
    }

}
