package com.insufficientlight.androidproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class BattleActivity extends GameActivity
{
    private static Battle battle = null;
    public static TextView Title;
    public TextView Army1;
    public TextView Army2;
    public TextView Terrain;
    public Button playerchoose;
    public TextView commandView;
    public  Button readyButton;
    public  Button retreatButton;
    public static final String TAG = "DATAPASSING";
    public String player = "player1";
    MultiplayerData multiplayerData = new MultiplayerData(); // The object that will hold importatn information pertaining to multiplayer functionality

    public String p1t;
    public String p1a;
    public String p1c;

    private Button Bat;
    private static int count;

    public String p2t;
    public String p2a;
    public String p2c;
    public static void setBattle (Battle yeet)
    {
        battle = yeet;
    }
    public void onCreate(Bundle savedInstanceState)
    {
        Multiplayer_Logic.setTwoData(multiplayerData.getCommandDecitionKey(), "player1","player2", "not","not");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        NumberPicker Formation = (NumberPicker) findViewById(R.id.form);
        NumberPicker ArcherTactics = (NumberPicker) findViewById(R.id.arch);
        NumberPicker CavalryTactics = (NumberPicker) findViewById(R.id.cav);

        Title = findViewById(R.id.titleView);
        Army1 = findViewById(R.id.attackerView);
        Army2 = findViewById(R.id.defenderView);
        Terrain = findViewById(R.id.terrainView);
        Bat = findViewById(R.id.button2);
        playerchoose = findViewById(R.id.button4);

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

        playerchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player = "player2";
                //Multiplayer_Logic.setSingleData(multiplayerData.getCommandDecitionKey(), player, "not");
            }
        });

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


                multiplayerData.getCommandDecitionKey().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful())
                        {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists())
                            {
                                if (document.getData().get("player1").equals("ready") && document.getData().get("player2").equals("ready"))
                                {runBat();}
                                if (player.equals("player1"))
                                {
                                    if (document.getData().get("player2").equals("ready"))
                                    {runBat();}
                                    else
                                    {Multiplayer_Logic.setTwoData(multiplayerData.getCommandDecitionKey(),"player1","player2","not","ready");}
                                }
                                if (player.equals("player2"))
                                {
                                    if (document.getData().get("player1").equals("ready"))
                                    {runBat();}
                                    else
                                    {Multiplayer_Logic.setTwoData(multiplayerData.getCommandDecitionKey(),"player1","player2","ready","not");}
                                }
                            }
                        }
                    }
                });



            }
        });

        multiplayerData.getCommandDecitionKey().addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists())
                {
                    if (documentSnapshot.getString("player1").equals("ready") && documentSnapshot.getString("player2").equals("ready"))
                    {runBat();}
                }
            }
        });

    }
    public void runBat()
    {
        Multiplayer_Logic.setTwoData(multiplayerData.getCommandDecitionKey(),"player1","player2","not","not");
        Log.i("Helen, help lol", "onClick: don't die keed");
        p2t = "Shield Wall";
        p2a = "Careful Volleys";
        p2c = "Charge Front Lines";
        StandardSkirmish skirmish = new StandardSkirmish(count, battle.attacker.playerTag, battle.defender.playerTag, battle,p1t, p1c, p1a, p2t, p2c, p2a);
        CombatEngine.calculateLosses(skirmish);
        count = count + 1;
        Title.setText("The battle of "+ battle.getLocation()+"!");
        Army1.setText("Attacker: " + battle.attacker.armyName);
        Army2.setText("Defender: " + battle.defender.armyName);
        Army1.append("\n Infantry: " + battle.getAttacker().getNumInf() + "\n Archers: " + battle.getAttacker().getNumArc() + "\n Cavalry :" + battle.getAttacker().getNumCav() +"\n Siege Weapons: " + battle.getAttacker().getNumSie());
        Army2.append("\n Infantry: " + battle.getDefender().getNumInf() + "\n Archers: " + battle.getDefender().getNumArc() + "\n Cavalry :" + battle.getDefender().getNumCav() +"\n Siege Weapons: " + battle.getDefender().getNumSie());
        Log.i("Sheed", "Noooo Halp");



        //The following lines of code create the ater dialog that show the total troops losses for each palyer
        //In the future player IDs will in some form be pulled from the battle object or simmiler
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        String displayString = ""; // Builds the content of the dialog from the data of combat engine.

        if (player.equals("player1"))
        {displayString = "Infantry Lost: " +  CombatEngine.attackerLosses + "\n Archers Lost: " +
            CombatEngine.attackerArcherLosses + "\n Cavalry Lost: " + CombatEngine.attackerCavLosses +
            "\n Seige Weapons Lost: " + CombatEngine.attackerSiegeLosses;}

        if (player.equals("player2"))
        {displayString = "Infantry Lost: " +  CombatEngine.defenderLosses + "\n Archers Lost: " +
                CombatEngine.defenderArcherLosses + "\n Cavalry Lost: " + CombatEngine.defenderCavLosses +
                "\n Seige Weapons Lost: " + CombatEngine.defenderSiegeLosses;}

        builder.setMessage(displayString).setCancelable(false).setNegativeButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle("Troops Lost In Battle");
        alert.show();
        // ends building the alert dialog
    }
}

//both players need to ready up, with confirmation on each device "player two is ready"
//runns code (bat button) give alert about total troops lost