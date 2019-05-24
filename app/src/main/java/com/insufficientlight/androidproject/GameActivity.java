package com.insufficientlight.androidproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class GameActivity extends AppCompatActivity
{
    public static Button StartBattleButton;
    public Button signInBut;
    private FirebaseAuth mAuth;
    private int RC_SIGN_IN = 1;
    public Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        final MultiplayerData multiplayerData = new MultiplayerData(); // The object that will hold important information pertaining to multi-player functionality

        //A builder used for any alert dialogs created from this class. It's located here to make calling them from onClickListeners easier
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        //Don't put stuff before the super.onCreate
        super.onCreate(savedInstanceState);

        //This variable holds the FirebaseAuth instance and is used in the sign in process
        mAuth = FirebaseAuth.getInstance();

        final List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());



        //These setup the default troop numbers ad situational variables for the sake of testing. In the future all of these would be set based on the situation the battle occurs in.
        setContentView(R.layout.activity_main);
        final Army army1 = new Army("Testville first company", 1,600, 300, 150 , 25 );
        final Army army2 = new Army("Testburg second legion", 2, 500, 300, 200, 30);
        final Terrain Planes = new Terrain("Planes", 300,  0, 2, 1, true,   false, false);
        final Terrain Hills = new Terrain("Hills", 200,  1, 1, 2, false,   true, false);
        final Terrain Fort = new Terrain("Fort", 50,  2, 1, 3, false,   false, true);
        final Unit infantry = new Unit("Infantry", 2, 1, false);
        final Unit archer = new Unit ("Archer", 2, 1, true);
        final Unit cavalry = new Unit ("Cavalry", 5, 2, false);
        final Unit siegeWeapon = new Unit ("Ballista", 1, 3, true);

        StartBattleButton = findViewById(R.id.startButton);
        signInBut = findViewById(R.id.signInButton);
        resetButton = findViewById(R.id.resetButton);



        //This is the button that moves the player into the battle itself. Prior to doing so it ensures that there is a user signed in. Along with this is creates the battle object.
        StartBattleButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (final View view)
            {
                //Gets the current user signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                //If someone has signed in, then start the battleActivity
                if (user != null) {
                    Battle battle1 = new Battle(army1, army2, Hills, "Yeetsburg");

                    BattleActivity.setBattle(battle1);
                    Intent myIntent = new Intent(view.getContext(), BattleActivity.class);
                    startActivityForResult(myIntent, 0);

                }
                //If there isn't someone signed in it will show an alert dialog warning the user.
                else {
                    // No user is signed in
                    dialogBuilder.setMessage("All users need to sign in before proceeding").setCancelable(false).setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alert = dialogBuilder.create();
                    alert.setTitle("Please Sign in");
                    alert.show();
                }
            }
            /*Thread thread = new Thread(new Runnable()
                {@Override
                    public void run()
                    {//Battle.simulateRound(army1, army2);
                       Intent myIntent = new Intent(view.getContext(), BattleActivity.class);
                       startActivityForResult(myIntent, 0);}});
                try
                {thread.join();}
                catch (InterruptedException e)
                {e.printStackTrace();}*/
        });

        //This button gets rid of the data in the UserId document.
        // This is nessesary because of the fact that this battle is on it's own, without it the defender/attacker will overlap
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplayerData.getmUserIdReferance().delete();
            }
        });

        //Clicking this button starts the prebuilt sign in intent/UI provided by Firebase. For the time being it only uses google accounts.
        // In the future other sign in options will be added, including google play games.
        signInBut.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View view)
        {
                // Create and launch sign-in intent
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .build(),
                        RC_SIGN_IN);
           }
        });
    }

    //Once the sign in has been completed this method will be called.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String email = user.getEmail();
                Log.i("user10101010", email);
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }



    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }*/


}





