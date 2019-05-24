package com.insufficientlight.androidproject;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MultiplayerData
{
    //Place holders for the data locations in the database.
    public DocumentReference mGameReferance = FirebaseFirestore.getInstance().document("games/game1");
    public DocumentReference mCombatCommandReferance = FirebaseFirestore.getInstance().document("games/game1/commands/combatCommands");
    public DocumentReference mCommandDecisionReference = FirebaseFirestore.getInstance().document("games/game1/commands/combatCommands");
    public DocumentReference mDefendLossesReference = FirebaseFirestore.getInstance().document("games/game1/combatData/battle1/losses/defenderLoss");
    public DocumentReference mAttackerLossesReference = FirebaseFirestore.getInstance().document("games/game1/combatData/battle1/losses/attackerLoss");
    public DocumentReference mUserIdReferance = FirebaseFirestore.getInstance().document("games/game1/users/userIds");



    public DocumentReference getCommandDecisionKey()
    { return mCommandDecisionReference; }
    public DocumentReference getGameReferance()
    { return mGameReferance; }
    public void setGameReferance(DocumentReference gameReferance)
    { mGameReferance = gameReferance; }
    public DocumentReference getmUserIdReferance()
    { return mUserIdReferance; }

    public DocumentReference getCombatCommandReferance()
    {
        return mCombatCommandReferance;
    }
    public void setCombatCommandReferance(DocumentReference combatCommandReferance)
    {
        mCombatCommandReferance = combatCommandReferance;
    }
    public DocumentReference getDefendLossesReferance()
    { return mDefendLossesReference; }

    public DocumentReference getAttackerLossesReferance()
    { return mAttackerLossesReference; }
    public void setDefendLossesReferance(DocumentReference defendLossesReferance)
    { mDefendLossesReference = defendLossesReferance; }




    public MultiplayerData()
    {

    }




}
