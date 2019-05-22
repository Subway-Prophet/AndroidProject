package com.insufficientlight.androidproject;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

//Contsins common methods used in setting, getting, and monitoring data in the database
public class Multiplayer_Logic
{
    MultiplayerData multiplayerData = new MultiplayerData();
    private static String out = "";


    public static void setSingleData(DocumentReference docRef, String key, Object data)
    {
        Map<String, Object> dataToSet = new HashMap<String, Object>();
        dataToSet.put(key,data);
        docRef.set(dataToSet);
    }


    public static void setTwoData(DocumentReference docRef, String key1,String key2, Object data, Object data2)
    {
        Map<String, Object> dataToSet = new HashMap<String, Object>();
        dataToSet.put(key1,data);
        dataToSet.put(key2,data2);
        docRef.set(dataToSet);
    }

    public static void setFiveData(DocumentReference docRef, String key1,String key2,String key3,String key4,String key5, Object data, Object data2,Object data3, Object data4,Object data5)
    {
        Map<String, Object> dataToSet = new HashMap<String, Object>();
        dataToSet.put(key1,data);
        dataToSet.put(key2,data2);
        dataToSet.put(key3,data3);
        dataToSet.put(key4,data4);
        dataToSet.put(key5,data5);
        docRef.set(dataToSet);
    }

    public static void setSixData(DocumentReference docRef, String key1,String key2,String key3,String key4,String key5,String key6, Object data, Object data2,Object data3, Object data4,Object data5,Object data6)
    {
        Map<String, Object> dataToSet = new HashMap<String, Object>();
        dataToSet.put(key1,data);
        dataToSet.put(key2,data2);
        dataToSet.put(key3,data3);
        dataToSet.put(key4,data4);
        dataToSet.put(key5,data5);
        dataToSet.put(key6,data6);
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
