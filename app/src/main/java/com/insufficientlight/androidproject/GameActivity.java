package com.insufficientlight.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    //Surrender Button
    public static Button Sur;
    //Keep fighting Button
    public static Button Con;
    //Losses text View for side one
    public static TextView Los1;
    //Losses text View for side two
    public static TextView Los2;
    //Army1's army name text view
    public static TextView An1;
    //Army2's army name text view
    public static TextView An2;
    //The Battle Start Button
    public static Button Bat;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Don't put stuff before the super.onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Army army1 = new Army("Testville first company", 1,300, 100, 0 , 10 );
        final Army army2 = new Army("Testburg second legion", 2, 200, 100, 75, 25);
        final Terrain planes = new Terrain("Planes", 300,  0, 2, 1, true,   false, false);
        final Terrain Hills = new Terrain("Hills", 200,  1, 1, 2, false,   true, false);
        final Terrain Fort = new Terrain("Fort", 50,  2, 1, 3, false,   false, true);
        final Unit infantry = new Unit("Infantry", 2, 1, false);
        final Unit archer = new Unit ("Archer", 2, 1, true);
        final Unit cavalry = new Unit ("Cavalry", 5, 2, false);
        final Unit siegeWeapon = new Unit ("Ballista", 1, 3, true);
        Sur = findViewById(R.id.Surrender);
        Con = findViewById(R.id.KeepFighting);
        Los1 = findViewById(R.id.ArmyOneLoss);
        Los2 = findViewById(R.id.ArmyTwoLoss);
        An1 = findViewById(R.id.ArmyOne);
        An2 = findViewById(R.id.ArmyTwo);
        Bat = findViewById(R.id.StartFight);
        Sur.setVisibility(View.GONE);
        Con.setVisibility(View.GONE);

        //CombatEngine.battleLoop( army1, army2);

    //Hey is this working

        Bat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Thread thread = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //CombatEngine.battleLoop(army1, army2);
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
            }
        });
    }

}





