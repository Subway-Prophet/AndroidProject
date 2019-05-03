package com.insufficientlight.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Don't put stuff before the super.onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Army army1 = new Army("Testville first company", 1, 0, 300);
        Army army2 = new Army("Testburg second legion", 1, 0, 300);
        CombatEngine.battleLoop( army1, army2);
    }
    //Hey is this working
}
