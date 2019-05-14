package com.insufficientlight.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class BattleActivity extends GameActivity
{
    private static Battle battle = null;
    public static TextView Title;
    public TextView Army1;
    public TextView Army2;
    public TextView Terrain;
    private Button Bat;
    private static int count;

    public String p1t;
    public String p1a;
    public String p1c;

    public String p2t;
    public String p2a;
    public String p2c;

    public static void setBattle (Battle yeet)
    {
        battle = yeet;
    }
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        NumberPicker Formation = (NumberPicker) findViewById(R.id.form);
        final NumberPicker ArcherTactics = (NumberPicker) findViewById(R.id.arch);
        NumberPicker CavalryTactics = (NumberPicker) findViewById(R.id.cav);

        Title = findViewById(R.id.titleView);
        Army1 = findViewById(R.id.attackerView);
        Army2 = findViewById(R.id.defenderView);
        Terrain = findViewById(R.id.terrainView);
        Bat = findViewById(R.id.button2);

        final String[] Formations = {"Shield Wall","Phalanx", "Turtle Formation"};
        final String[] ArcTac = {"Careful Volleys", "Full Volleys", "Protect Flanks"};
        final String[] CavTac = {"Charge Front Lines","Flanking Operation", "Hold Cavalry"};

        Formation.setMinValue(0);
        ArcherTactics.setMinValue(0);
        CavalryTactics.setMinValue(0);

        Formation.setMaxValue(2);
        ArcherTactics.setMaxValue(2);
        CavalryTactics.setMaxValue(2);

        Formation.setDisplayedValues(Formations);
        ArcherTactics.setDisplayedValues(ArcTac);
        CavalryTactics.setDisplayedValues(CavTac);

        Formation.setWrapSelectorWheel(true);
        ArcherTactics.setWrapSelectorWheel(true);
        CavalryTactics.setWrapSelectorWheel(true);

        Terrain.setText(battle.getTerrain().getTerrainType());
        Title.setText("The battle of "+ battle.getLocation()+"!");
        Army1.setText("Attacker: " + battle.attacker.armyName);
        Army2.setText("Defender: " + battle.defender.armyName);
        Army1.append("\n Infantry: " + battle.attacker.numInf + "\n Archers: " + battle.attacker.numArc + "\n Cavalry :" + battle.attacker.numCav +"\n Siege Weapons: " + battle.attacker.numSie);
        Army2.append("\n Infantry: " + battle.defender.numInf + "\n Archers: " + battle.defender.numArc + "\n Cavalry :" + battle.defender.numCav +"\n Siege Weapons: " + battle.defender.numSie);

        Formation.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                p1t = Formations[newVal];
            }
        });

        ArcherTactics.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                p1a = ArcTac[newVal];
            }
        });

        CavalryTactics.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                p1c = CavTac[newVal];
            }
        });


        Bat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Thread thread = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        p2t = "Shield Wall";
                        p2a = "Careful Volleys";
                        p2c = "Charge Front Lines";
                        StandardSkirmish skirmish = new StandardSkirmish(count, battle.attacker.playerTag, battle.defender.playerTag, battle,                                                 p1t, p1c, p1a, p2t, p2c, p2a);
                        count = count + 1;
                        Terrain.setText("It woiked");
                    }
                });
            }
        });
    }
}
