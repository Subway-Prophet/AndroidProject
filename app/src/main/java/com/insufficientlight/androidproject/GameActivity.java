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
        final Army army1 = new Army("Testville first company", 1, 300);
        final Army army2 = new Army("Testburg second legion", 1,  300);

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
            CombatEngine.battleLoop(army1, army2);
        }
    });
}

}





