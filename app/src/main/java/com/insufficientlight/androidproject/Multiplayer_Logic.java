package com.insufficientlight.androidproject;

import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class Multiplayer_Logic {
    MultiplayerData multiplayerData = new MultiplayerData();

    public static void setData(DocumentReference docRef, String key, Object data, String methodCommand) {
        if (methodCommand.equals("singleString")) {
            Map<String, Object> dataToSet = new HashMap<String, Object>();
            dataToSet.put(key, data);
            docRef.set(dataToSet);
        }
    }


    public static void setBattleData(DocumentReference docRef, Battle battle)
    {
        Map<String, Object> dataToSet = new HashMap<String, Object>();
        dataToSet.put("attacker", battle.getAttacker() );
        dataToSet.put("Defender", battle.getDefender() );
        dataToSet.put("Terain", battle.getTerrain() );
        dataToSet.put("Location", battle.getLocation() );
        docRef.set(dataToSet);
    }

}


