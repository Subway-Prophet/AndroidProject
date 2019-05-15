package com.insufficientlight.androidproject;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MultiplayerData
{
    //Place holders for the data locations in the database.
    public DocumentReference mGameReferance = FirebaseFirestore.getInstance().document("games/game1");
    public DocumentReference mCombatCommandReferance = FirebaseFirestore.getInstance().document("games/game1/commands/combatCommands");
    public DocumentReference mBattleDataReferance = FirebaseFirestore.getInstance().document("games/game1/combatobject");
    public String mCommandDecitionKey = "decitionCommand";


    public DocumentReference getBattleDataReferance()
    { return mBattleDataReferance; }
    public void setBattleDataReferance(DocumentReference battleDataReferance)
    { mBattleDataReferance = battleDataReferance; }
    public String getCommandDecitionKey()
    { return mCommandDecitionKey; }
    public DocumentReference getGameReferance()
    { return mGameReferance; }
    public void setGameReferance(DocumentReference gameReferance)
    { mGameReferance = gameReferance; }

    public DocumentReference getCombatCommandReferance()
    { return mCombatCommandReferance; }
    public void setCombatCommandReferance(DocumentReference combatCommandReferance)
    { mCombatCommandReferance = combatCommandReferance; }




    public MultiplayerData()
    {

    }




}
