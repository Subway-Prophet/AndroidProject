package com.insufficientlight.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity
{
    public static Button Bat;
    MultiplayerData multiplayerData = new MultiplayerData();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Don't put stuff before the super.onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Army army1 = new Army("Testville first company", 1,300, 100, 0 , 10 );
        final Army army2 = new Army("Testburg second legion", 2, 200, 100, 75, 25);
        final Terrain Planes = new Terrain("Planes", 300,  0, 2, 1, true,   false, false);
        final Terrain Hills = new Terrain("Hills", 200,  1, 1, 2, false,   true, false);
        final Terrain Fort = new Terrain("Fort", 50,  2, 1, 3, false,   false, true);
        final Unit infantry = new Unit("Infantry", 2, 1, false);
        final Unit archer = new Unit ("Archer", 2, 1, true);
        final Unit cavalry = new Unit ("Cavalry", 5, 2, false);
        final Unit siegeWeapon = new Unit ("Ballista", 1, 3, true);
        Bat = findViewById(R.id.button);

        //CombatEngine.battleLoop( army1, army2);

    //Hey is this working

        Bat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (final View view)
            {
                Battle battle1 = new Battle(army1, army2, Hills, "Yeetsburg");
                Multiplayer_Logic.setBattleData(multiplayerData.getBattleDataReferance(), battle1);
                BattleActivity.setBattle(battle1);
                Intent myIntent = new Intent(view.getContext(), BattleActivity.class);
                startActivityForResult(myIntent, 0);
            }
                /**Thread thread = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                       //Battle.simulateRound(army1, army2);
                       Intent myIntent = new Intent(view.getContext(), BattleActivity.class);
                       startActivityForResult(myIntent, 0);
                    }
                });
                try
                {
                    thread.join();
                }
                catch (InterruptedException e)
                {
                e.printStackTrace();
                }
            }**/
        });
    }

}





