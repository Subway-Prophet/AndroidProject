package com.insufficientlight.androidproject;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class Multiplayer_Logic
{
    MultiplayerData multiplayerData = new MultiplayerData();
    private static String out = "";

    public static void setData(DocumentReference docRef, String key, Object data)
    {
        Map<String, Object> dataToSet = new HashMap<String, Object>();
        dataToSet.put(key,data);
        docRef.set(dataToSet);

    }

    public static void sendBattleResults(DocumentReference docref, String key, Object data)
    {

    }

    public static String commandListener(DocumentReference docref, final String key)
    {

        docref.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {
                    out = documentSnapshot.getString(key);
                }
                else out="nothing";
            }
        });
        return out;
    }



}
