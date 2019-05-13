package com.insufficientlight.androidproject;

import java.util.ArrayList;

public class Battle
{
    Army attacker = null;
    Army defender = null;
    Terrain Terrain = null;
    String Location = null;

    public Army getAttacker()
    {
        return attacker;
    }

    public void setAttacker(Army attacker)
    {
        this.attacker = attacker;
    }

    public Army getDefender()
    {
        return defender;
    }

    public void setDefender(Army defender)
    {
        this.defender = defender;
    }

    public String getLocation()
    {
        return Location;
    }

    public void setLocation(String location)
    {
        Location = location;
    }

    public Terrain getTerrain()
    {
        return Terrain;
    }

    public Battle(Army  attacker, Army defender, Terrain Terrain, String Location)
    {
        this.attacker = attacker;
        this.defender = defender;
        this.Terrain = Terrain;
        this.Location = Location;
    }
}
