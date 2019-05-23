package com.insufficientlight.androidproject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    public String p1t;
    public String p1a;
    public String p1c;

    private Button Bat;
    private static int count;

    public String p2t;
    public String p2a;
    public String p2c;

    public long defendInf;
    public long defendArch;
    public long defendCav;
    public long defendSiege;

    public long attackInf;
    public long attackArch;
    public long attackCav;
    public long attackSeige;
    public int batRan;

    public static void setBattle (Battle yeet)
    {
        battle = yeet;
    }
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        // sets defaults, will probably be changed in the future as more complexity happens.


        //Shows prograss dialog while important tasks are completed. Will be dissmissed after player data is set
        final ProgressDialog pDialog = ProgressDialog.show(BattleActivity.this,
                "Please Wait",
                "Loading...",
                true);


        //Sets the userId to the Uid(unique ID) provided by firebase for the signed in user
        final String  userID = user.getUid();


        //Batrun is a control variable that ensures the combat engine only runs once per cycle
        batRan = 0;


        // creates the builder that will be used for alert dialogs
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);


        //Clears out the commands document prior the rest of the code running
        Multiplayer_Logic.setTwoData(multiplayerData.getCommandDecisionKey(), "attacker1","defender1", "not","not");


        //Number pickers used for strategy
        NumberPicker Formation = (NumberPicker) findViewById(R.id.form);
        NumberPicker CavalryTactics = (NumberPicker) findViewById(R.id.cav);


        Title = findViewById(R.id.titleView);
        Army1 = findViewById(R.id.attackerView);
        Army2 = findViewById(R.id.defenderView);
        Terrain = findViewById(R.id.terrainView);
        Bat = findViewById(R.id.button2);

        //Set up for the tactics spinners
        final String[] Formations = {"Shield Wall","Phalanx", "Turtle Formation"};
        final String[] CavTac = {"Charge Front Lines","Flanking Operation", "Hold Cavalry"};

        Formation.setMinValue(0);
        CavalryTactics.setMinValue(0);

        Formation.setMaxValue(2);
        CavalryTactics.setMaxValue(2);

        Formation.setDisplayedValues(Formations);
        CavalryTactics.setDisplayedValues(CavTac);

        Formation.setWrapSelectorWheel(true);
        CavalryTactics.setWrapSelectorWheel(true);


        //Displays the battle information for both sides, the terrain, and the name of the area they're fighting in.
        Terrain.setText(battle.getTerrain().getTerrainType());
        Title.setText("The battle of "+ battle.getLocation()+"!");
        Army1.setText("Attacker: " + battle.attacker.armyName);
        Army2.setText("Defender: " + battle.defender.armyName);
        Army1.append("\n Infantry: " + battle.attacker.numInf + "\n Archers: " + battle.attacker.numArc + "\n Cavalry :" + battle.attacker.numCav +"\n Siege Weapons: " + battle.attacker.numSie);
        Army2.append("\n Infantry: " + battle.defender.numInf + "\n Archers: " + battle.defender.numArc + "\n Cavalry :" + battle.defender.numCav +"\n Siege Weapons: " + battle.defender.numSie);


        /**
         * The following button starts a set of tasks that accomplish the following:
         * -Determine if the userIDs in the data abse are new or old,
         * -Determine if there is another user already set
         * -If a user is there it sets the current user as the opposite
         * -If there is none it picks randomly between attacker/defender and uploads the data
         */
        multiplayerData.getmUserIdReferance().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    if (documentSnapshot.contains("playCheck")) {
                        if (documentSnapshot.get("playCheck").equals("true")) {
                            if (Math.random() < 0.5) {
                                Multiplayer_Logic.setTwoData(multiplayerData.mUserIdReferance, "attacker", "playCheck", userID, "false");
                                player = "attacker1";
                                Title.append(" You are the ATTACKER1");
                            } else {
                                Multiplayer_Logic.setTwoData(multiplayerData.mUserIdReferance, "defender", "playCheck", userID, "false");
                                player = "defender1";
                                Title.append(" You are the DEFENDER1");
                            }
                        }
                        else {
                            if (documentSnapshot.contains("attacker") && !documentSnapshot.contains("defender")) {
                                Multiplayer_Logic.setThreeData(multiplayerData.mUserIdReferance, "attacker", "defender", "playCheck", documentSnapshot.getString("attacker"), userID, "false");
                                player = "defender1";
                                Title.append(" You are the DEFENDER2");
                            } else if (documentSnapshot.contains("defender") && !documentSnapshot.contains("attacker")) {
                                Multiplayer_Logic.setThreeData(multiplayerData.mUserIdReferance, "attacker", "defender","playCheck", userID, documentSnapshot.getString("defender"),"false");
                                player = "attacker1";
                                Title.append(" You are the ATTACKER2");
                            } else {
                                if (Math.random() < 0.5) {
                                    Multiplayer_Logic.setTwoData(multiplayerData.mUserIdReferance, "attacker", "playCheck", userID, "false");
                                    player = "attacker1";
                                    Title.append(" You are the ATTACKER3");
                                } else {
                                    Multiplayer_Logic.setTwoData(multiplayerData.mUserIdReferance, "defender", "playCheck", userID, "false");
                                    player = "defender1";
                                    Title.append(" You are the DEFENDER3");
                                }
                            }
                        }
                    }
                    else
                    {
                        if (Math.random() < 0.5) {
                            Multiplayer_Logic.setTwoData(multiplayerData.mUserIdReferance, "attacker", "playCheck", userID, "false");
                            player = "attacker1";
                            Title.append(" You are the ATTACKER");
                        } else {
                            Multiplayer_Logic.setTwoData(multiplayerData.mUserIdReferance, "defender", "playCheck", userID, "false");
                            player = "defender1";
                            Title.append(" You are the DEFENDER");
                        }
                    }
                }
                cancelLoadDialog(pDialog);
            }
        });




        //Listeners for the tactics spinners
        Formation.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                p1t = Formations[newVal];
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


        /**
         * When the ready button is clicked this method will run. It handles setting commands on the database and running them if possible.
         */
        Bat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {Log.i("testy", player);
                //Handles setting the commands in the database.
                //Starts by pulling down the current copy of the document
                multiplayerData.getCommandDecisionKey().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        batRan = 0;
                        if (task.isSuccessful())
                        {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) //Gets the data and reifies it exists
                            {
                                if (player.equals("attacker1"))//Breaks down actions depending on the player taking them, both are identical
                                {
                                    Log.i("testy", player);
                                    if (document.getData().get("defender1").equals("ready")) //if the other player has already hit their button
                                    {
                                        runBat("attacker"); // runs the combat mechanics for the attacker
                                    }
                                    else
                                    {Multiplayer_Logic.setTwoData(multiplayerData.getCommandDecisionKey(),"attacker1","defender1","ready","not");} // changes player1's status command to ready
                                }
                                if (player.equals("defender1"))
                                {Log.i("testy", player);
                                    if (document.getData().get("attacker1").equals("ready"))//if the other player has already hit their button it sets both to ready. allowing the attacker to run their cooodesss
                                    {
                                        Multiplayer_Logic.setTwoData(multiplayerData.getCommandDecisionKey(),"attacker1","defender1","ready","ready");
                                        //runBat();
                                    }
                                    else
                                    {Multiplayer_Logic.setTwoData(multiplayerData.getCommandDecisionKey(),"attacker1","defender1","not","ready");}// changes player2's status command to ready
                                }
                            }
                        }
                    }
                });
            }
        });



        /**
         * Monitors the battle command document for any changes, if both are set to ready it runs battle loop for the attacker only
         */
        multiplayerData.getCommandDecisionKey().addSnapshotListener(new EventListener<DocumentSnapshot>()
        {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e)
            {

                if (documentSnapshot.exists())
                {
                    if (documentSnapshot.getString("attacker1").equals("ready") && documentSnapshot.getString("defender1").equals("ready") && player.equals("attacker1"))
                    {runBat("attacker");}
                }
            }
        });


        /**
         * When the attacker changes the defender loss data in the database this method is called, it then sets the local variables for the losses and runs the battle loop for the defender, creating the popup
         */
        multiplayerData.getDefendLossesReferance().addSnapshotListener(new EventListener<DocumentSnapshot>()
        {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e)
            {
                if (documentSnapshot.exists())
                {
                    Log.i("data10101", "before " + player + " " + documentSnapshot.getString("defenderID"));
                    if (documentSnapshot.getString("defenderID").equals(player))
                    {
                        Log.i("data10101", "working");
                        defendInf = (long) documentSnapshot.getData().get("inf");
                        defendArch =(long) documentSnapshot.getData().get("arch");
                        defendCav = (long)documentSnapshot.getData().get("cav");
                        defendSiege = (long)documentSnapshot.getData().get("siege");

                        //This retreaves the attacker army size from the database to be displayed on screen. Once it's done it runs the method runBat()
                        multiplayerData.getAttackerLossesReferance().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists())
                                {
                                    attackInf = (long) documentSnapshot.getData().get("inf");
                                    attackArch =(long) documentSnapshot.getData().get("arch");
                                    attackCav = (long)documentSnapshot.getData().get("cav");
                                    attackSeige = (long)documentSnapshot.getData().get("siege");
                                    runBat("defender");
                                }
                            }
                        });
                    }
                }
            }
        });


    }


    /**
     * RunBat is the method called to run the combat engine when both players are ready. In doing so it completes the following tasks
     * -Determines if it is the defender or attacker running the method.
     *
     * If it's the attacker it will run combatEngine, update the troop numbers on screen, show an alertDialog with troop losses, and uploads the new troop counts to the database for the defender to pull.
     *
     * If it's the defender it will  be trigered by the database being updated.
     * It then updates the troop counts with the pulled data, and displaies an alertDialog with the troop losses.
     *
     */
    public void runBat(String side)
    {
        multiplayerData.getmUserIdReferance().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Multiplayer_Logic.setThreeData(multiplayerData.mUserIdReferance, "attacker", "defender", "playCheck", documentSnapshot.getString("attacker"), documentSnapshot.getString("defender"), "true");
            }
        });
        if (batRan == 0) {
            batRan = 1;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            String displayString = "";

        if (side.equals("attacker"))
        {
        Log.i("Helen, help lol", "onClick: don't die keed");
        p2t = "Shield Wall";
        p2c = "Charge Front Lines";

        StandardSkirmish skirmish = new StandardSkirmish(count, battle.attacker.playerTag, battle.defender.playerTag, battle,p1t, p1c, p2t, p2c);
        CombatEngine.calculateLosses(skirmish);
        count = count + 1;
        //Title.setText("The battle of "+ battle.getLocation()+"!");
        Army1.setText("Attacker: " + battle.attacker.armyName);
        Army2.setText("Defender: " + battle.defender.armyName);
        Army1.append("\n Infantry: " + battle.getAttacker().getNumInf() + "\n Archers: " + battle.getAttacker().getNumArc() + "\n Cavalry :" + battle.getAttacker().getNumCav() +"\n Siege Weapons: " + battle.getAttacker().getNumSie());
        Army2.append("\n Infantry: " + battle.getDefender().getNumInf() + "\n Archers: " + battle.getDefender().getNumArc() + "\n Cavalry :" + battle.getDefender().getNumCav() +"\n Siege Weapons: " + battle.getDefender().getNumSie());


                Log.i("Sheed", "Noooo Halp");



                //The following lines of code create the atert dialog that show the total troops losses for each palyer
                //In the future player IDs will in some form be pulled from the battle object or simmiler
                // Builds the content of the dialog from the data of combat engine.
                displayString = "Infantry Lost: " + CombatEngine.attackerLosses + "\n Archers Lost: " +
                        CombatEngine.attackerArcherLosses + "\n Cavalry Lost: " + CombatEngine.attackerCavLosses +
                        "\n Seige Weapons Lost: " + CombatEngine.attackerSiegeLosses;




                builder.setMessage(displayString).setCancelable(false).setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("Troops Lost In Battle");
                alert.show();
                Multiplayer_Logic.setTwoData(multiplayerData.getCommandDecisionKey(), "attacker1", "defender1", "not", "not");




                // ends building the alert dialog

                Multiplayer_Logic.setSixData(multiplayerData.getAttackerLossesReferance(), "attackerID", "inf", "arch", "cav", "siege", "changeCheck","attacker1", skirmish.getBattle().getAttacker().getNumInf(), skirmish.getBattle().getAttacker().getNumArc(), skirmish.getBattle().getAttacker().getNumCav(), skirmish.getBattle().getAttacker().getNumSie(),"changed"+Math.random());
                Multiplayer_Logic.setSixData(multiplayerData.getDefendLossesReferance(), "defenderID", "inf", "arch", "cav", "siege",  "changeCheck","defender1", skirmish.getBattle().getDefender().getNumInf(), skirmish.getBattle().getDefender().getNumArc(), skirmish.getBattle().getDefender().getNumCav(), skirmish.getBattle().getDefender().getNumSie(),"changed"+Math.random());

            }

            if (side.equals("defender")) {
                Log.i("1010", "DEFENDERBATTLLE CALLED");
                Log.i("110101001111011", "working " + defendInf);


                //sets the new army size for the defender defend
                int infLoss = battle.getDefender().getNumInf() - (int) defendInf;
                int cavLoss = battle.getDefender().getNumCav()-(int) defendCav;
                int archLoss = battle.getDefender().getNumArc()-(int) defendArch;
                int sieLoss = battle.getDefender().getNumSie()-(int) defendSiege;


                battle.getDefender().setNumInf((int) defendInf);
                battle.getDefender().setNumCav((int) defendCav);
                battle.getDefender().setNumArc((int) defendArch);
                battle.getDefender().setNumSie((int) defendSiege);

                //set the new army size for the attacker
                battle.getAttacker().setNumInf((int) attackInf);
                battle.getAttacker().setNumCav((int) attackCav);
                battle.getAttacker().setNumArc((int) attackArch);
                battle.getAttacker().setNumSie((int) attackSeige);
                Log.i("defffffff", "defffffff tot " + battle.getDefender().getNumCav());

                Army1.setText("Attacker: " + battle.attacker.armyName);
                Army2.setText("Defender: " + battle.defender.armyName);
                Army2.append("\n Infantry: " + battle.getDefender().getNumInf() + "\n Archers: " + battle.getDefender().getNumArc() + "\n Cavalry :" + battle.getDefender().getNumCav() + "\n Siege Weapons: " + battle.getDefender().getNumSie());
                Army1.append("\n Infantry: " + battle.getAttacker().getNumInf() + "\n Archers: " + battle.getAttacker().getNumArc() + "\n Cavalry :" + battle.getAttacker().getNumCav() + "\n Siege Weapons: " + battle.getAttacker().getNumSie());


                //creates the string to display from losses data
                displayString = "Infantry Lost: " + infLoss + "\n Archers Lost: " +
                        archLoss + "\n Cavalry Lost: " + cavLoss +
                        "\n Seige Weapons Lost: " + sieLoss;

                //builds the alert dialog
                builder.setMessage(displayString).setCancelable(false).setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });
                //sets the title and shows it
                AlertDialog alert = builder.create();
                alert.setTitle("Troops Lost In Battle12");
                alert.show();

                }
        }
    }

    public void cancelLoadDialog(ProgressDialog dialog)
    {
        dialog.dismiss();
    }

}


