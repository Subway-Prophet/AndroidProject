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
    public static Button Bat;
    public Button signInBut;
    private FirebaseAuth mAuth;
    private int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //A builder used for any alert dialogs created from this class. It's located here to make calling them from onClickListeners easier
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        //Don't put stuff before the super.onCreate
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        final List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());




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

        Bat = findViewById(R.id.startButton);
        signInBut = findViewById(R.id.signInButton);


        //CombatEngine.battleLoop( army1, army2);

    //Hey is this working

        Bat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (final View view)
            {




                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Battle battle1 = new Battle(army1, army2, Hills, "Yeetsburg");

                    BattleActivity.setBattle(battle1);
                    Intent myIntent = new Intent(view.getContext(), BattleActivity.class);
                    startActivityForResult(myIntent, 0);

                } else {
                    // No user is signed in
                    dialogBuilder.setMessage("Please Sign in").setCancelable(false).setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alert = dialogBuilder.create();
                    alert.setTitle("All users need to sign in before proceeding");
                    alert.show();

                }



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

        signInBut.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View view) {

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

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


}





