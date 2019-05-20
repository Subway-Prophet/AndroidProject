package com.insufficientlight.androidproject;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class DataPassing_Firestore
{
    public static final String TAG = "DATAPASSING";
    //Creates an instance of Firestore, this will be used to transfer data for the combat engine.
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static DocumentReference docRef = db.collection("users").document("User1");
    //private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("users/userdata");

    //method to test putting data to the database
    public static void testData()
    {
        Map<String, Object> user = new HashMap<String, Object>();
        user.put("first", "Jason");
        user.put("last", "Baldwin");


         db.collection("users")
                 .add(user)
                 .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                     @Override
                     public void onSuccess(DocumentReference documentReference) {
                         Log.i(TAG, documentReference.getId());
                         docRef = documentReference;
                     }
                 })
                 .addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Log.e(TAG, "SaveFail " + e.getMessage());
                     }
                 });



         db.collection("users")
                 .get()
                 .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                     @Override
                     public void onComplete(@NonNull Task<QuerySnapshot> task) {
                         if (task.isSuccessful())
                         {
                             for (QueryDocumentSnapshot document: task.getResult())
                             {
                                 Log.d(TAG, "getting data1"+document.getId() + " => " + document.getData());
                             }
                         }
                         else
                         {
                             Log.w(TAG, "Error getting documents.", task.getException());
                         }
                     }
                 });

         db.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
             @Override
             public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {

             }
         });





         docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
             @Override
             public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                 if (documentSnapshot.exists())
                 {
                     Log.d(TAG, "getting data2 " + documentSnapshot.getId() + " " + documentSnapshot.getString("first"));
                 }
             }
         });

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                String source = snapshot != null && snapshot.getMetadata().hasPendingWrites()
                        ? "Local" : "Server";

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, source + " data: " + snapshot.getData());
                } else {
                    Log.d(TAG, source + " data: null");
                }
            }
        });


    }

    public static void listen()
    {
        db.collection("users").document("xlDl9UcWmBYL0wsjLKjT").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                Log.i(TAG, "getting data3 " + documentSnapshot.getId() + " " + documentSnapshot.getString("first"));
            }
        });
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists())
                {
                    Log.i(TAG, "getting data 4" + documentSnapshot.getId() + " " + documentSnapshot.getString("first"));
                }
            }
        });

    }
}
