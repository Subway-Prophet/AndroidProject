package com.insufficientlight.androidproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class BattleActivity extends GameActivity
{
    private static Battle battle = null;
    public static TextView Title;
    public TextView Army1;
    public TextView Army2;
    public TextView Terrain;
    public TextView commandView;
    public  Button readyButton;
    public  Button retreatButton;
    public static final String TAG = "DATAPASSING";
    MultiplayerData multiplayerData = new MultiplayerData(); // The object that will hold importatn information pertaining to multiplayer functionality
    public static void setBattle (Battle yeet)
    {
        battle = yeet;
    }
    public void onCreate(Bundle savedInstanceState)
    {
        //DataPassing_Firestore.testData();

        //DataPassing_Firestore.listen();

        //Creates the base object for firestore transactions
         //FirebaseFirestore db = FirebaseFirestore.getInstance();

        /*db.collection("users").document("xlDl9UcWmBYL0wsjLKjT").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                Log.i(TAG, "getting data5 " + documentSnapshot.getId() + " " + documentSnapshot.getString("first"));
            }
        });*/

        //creates the base 'map' that will be loaded into the database.
        //Map<String, Object> game = new HashMap<String, Object>();
         //       game.put("command", "test");
      //  mDocRef.set(game).addOnSuccessListener(new OnSuccessListener<Void>() {
         //   @Override
        //    public void onSuccess(Void aVoid) {
         //       Log.i(TAG, "First Data Saved")
        //    }
      //  })

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        NumberPicker Formation = (NumberPicker) findViewById(R.id.form);
        NumberPicker ArcherTactics = (NumberPicker) findViewById(R.id.arch);
        NumberPicker CavalryTactics = (NumberPicker) findViewById(R.id.cav);

        Title = findViewById(R.id.titleView);
        Army1 = findViewById(R.id.attackerView);
        Army2 = findViewById(R.id.defenderView);
        Terrain = findViewById(R.id.terrainView);
        readyButton = findViewById(R.id.button2);
        retreatButton = findViewById(R.id.button3);
        commandView=findViewById(R.id.Commands);

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

        retreatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Multiplayer_Logic.setData(multiplayerData.getCombatCommandReferance(), multiplayerData.getCommandDecitionKey(), "retreat");
            }
        });



        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Multiplayer_Logic.setData(multiplayerData.getCombatCommandReferance(), multiplayerData.getCommandDecitionKey(), "ready");
            }
        });

        multiplayerData.getCombatCommandReferance().addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists())
                {
                    commandView.setText(documentSnapshot.getString(multiplayerData.getCommandDecitionKey()));
                }
            }
        });

    }
}
