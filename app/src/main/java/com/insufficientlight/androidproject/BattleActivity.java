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
import com.google.firebase.firestore.FirebaseFirestore;
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Multiplayer_Logic.setTwoData(multiplayerData.getCommandDecitionKey(), "player1","player2", "not","not"); // sets defualts, will probably be changed in the future as more complexity arrises.

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
            {Log.i("testy", player);

                //Handles setting the commands in the database.
                //Starts by pulling down the current copy of the document
                multiplayerData.getCommandDecitionKey().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful())
                        {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) //Gets the data and reifies it exists
                            {
                                if (document.getData().get("player1").equals("ready") && document.getData().get("player2").equals("ready")) // Ensures both aren't already set to ready.
                                {runBat();}
                                if (player.equals("player1"))//Breaks down actions depending on the player taking them, both are identical
                                {
                                    Log.i("testy", player);
                                    if (document.getData().get("player2").equals("ready")) //if the other player has already hit their button
                                    {
                                        Multiplayer_Logic.setTwoData(multiplayerData.getCommandDecitionKey(),"player1","player2","ready","ready"); // finalizes the command so the other devices know to run
                                        runBat(); // runs the combat mechanics
                                    }
                                    else
                                    {Multiplayer_Logic.setTwoData(multiplayerData.getCommandDecitionKey(),"player1","player2","ready","not");}
                                }
                                if (player.equals("player2"))
                                {Log.i("testy", player);
                                    if (document.getData().get("player1").equals("ready"))
                                    {
                                        Multiplayer_Logic.setTwoData(multiplayerData.getCommandDecitionKey(),"player1","player2","ready","ready");
                                        runBat();
                                    }
                                    else
                                    {Multiplayer_Logic.setTwoData(multiplayerData.getCommandDecitionKey(),"player1","player2","not","ready");}
                                }
                            }
                        }
                    }
                });



            }
        });

        //Monitors the battle command document for any changes, if both are set to ready it runs battle loop
        multiplayerData.getCommandDecitionKey().addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (documentSnapshot.exists())
                {
                    Log.i("InSnapshot", documentSnapshot.getId());
                    Log.i("InSnapshot", documentSnapshot.getString("player1"));
                    Log.i("InSnapshot", documentSnapshot.getString("player2"));
                    if (documentSnapshot.getString("player1").equals("ready") && documentSnapshot.getString("player2").equals("ready"))
                    {runBat();}
                }
            }
        });

        multiplayerData.getDefendLossesReferance().addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
            if (documentSnapshot.exists())
            {
                if (documentSnapshot.getString("defenderID").equals(player))
                {
                    int infLoss = (int) documentSnapshot.getData().get("infLosses");
                    int archLoss =(int) documentSnapshot.getData().get("infLosses");
                    int cavLoss = (int)documentSnapshot.getData().get("infLosses");
                    int seigeLoss = (int)documentSnapshot.getData().get("infLosses");

                    String displayString;


                    displayString = "Infantry Lost: " + infLoss+ "\n Archers Lost: " +
                                archLoss + "\n Cavalry Lost: " + cavLoss +
                                "\n Seige Weapons Lost: " + seigeLoss;


                    builder.setMessage(displayString).setCancelable(false).setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Troops Lost In Battle1");
                    alert.show();
                }

            }


            }
        });


    }
    //The battle loop exicuting billy's code
    public void runBat()
    {
        //android.os.SystemClock.sleep(500);
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

       // FirebaseFirestore.getInstance().document("games/game1/Armys/army1").set(Army1);

        Log.i("Sheed", "Noooo Halp");



        //The following lines of code create the atert dialog that show the total troops losses for each palyer
        //In the future player IDs will in some form be pulled from the battle object or simmiler
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        String displayString = ""; // Builds the content of the dialog from the data of combat engine.

        //For testing player1 is the attacker and player2 is the defender. In the future this would be determined through stored player ids and their actions
        if (player.equals("player1"))
        {displayString = "Infantry Lost: " +  CombatEngine.attackerLosses + "\n Archers Lost: " +
            CombatEngine.attackerArcherLosses + "\n Cavalry Lost: " + CombatEngine.attackerCavLosses +
            "\n Seige Weapons Lost: " + CombatEngine.attackerSiegeLosses;

        int infLoss = CombatEngine.defenderLosses;
        int archLosses = CombatEngine.defenderArcherLosses;
        int cavLosses = CombatEngine.defenderCavLosses;
        int seigeLosses = CombatEngine.defenderSiegeLosses;

        Multiplayer_Logic.setFiveData(multiplayerData.getDefendLossesReferance(), "defenderID","infLosses","archLosses","cavLosses","seigeLosses","player2", infLoss,archLosses,cavLosses,seigeLosses);



        }



        builder.setMessage(displayString).setCancelable(false).setNegativeButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle("Troops Lost In Battle");
        alert.show();
        Multiplayer_Logic.setTwoData(multiplayerData.getCommandDecitionKey(),"player1","player2","not","not");
        // ends building the alert dialog


    }

    public void displayLosses(int infLoss, int archLoss,int cavLoss, int seigeLoss) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String displayString = "";

        if (player.equals("player2")) {
            displayString = "Infantry Lost: " + infLoss+ "\n Archers Lost: " +
                    archLoss + "\n Cavalry Lost: " + cavLoss +
                    "\n Seige Weapons Lost: " + seigeLoss;
        }

        builder.setMessage(displayString).setCancelable(false).setNegativeButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle("Troops Lost In Battle");

    }


}

