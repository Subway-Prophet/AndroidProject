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

        //CombatEngine.battleLoop( "Testville ", "Testburg");
    }
}
