package com.insufficientlight.androidproject;

import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class Multiplayer_Logic
{
    MultiplayerData multiplayerData = new MultiplayerData();

    public static void setData(DocumentReference docRef, String key, Object data)
    {
        Map<String, Object> dataToSet = new HashMap<String, Object>();
        dataToSet.put(key,data);
        docRef.set(dataToSet);

    }



}
