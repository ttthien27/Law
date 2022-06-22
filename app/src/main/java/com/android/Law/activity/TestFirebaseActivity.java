package com.android.Law.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.Law.R;
import com.android.Law.firebase.LawDatabaseContract;
import com.android.Law.models.DocumentMostView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TestFirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_firebase);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://appphapluat-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference();

        List<DocumentMostView> listDocumentView = new ArrayList<DocumentMostView>();
        DatabaseReference databaseReference = myRef.child(LawDatabaseContract.DocumentEntry.ROOT_NAME_TOP);
        Query query = databaseReference;
        query.addValueEventListener(new ValueEventListener()  {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String key = snapshot.getKey();
                        DocumentMostView doc;
                        String id =dataSnapshot.child(LawDatabaseContract.DocumentEntry.ID_ARM).getValue(String.class);
                        String url =dataSnapshot.child(LawDatabaseContract.DocumentEntry.URL_ARM).getValue(String.class);
                        String title =dataSnapshot.child(LawDatabaseContract.DocumentEntry.TITLE_ARM).getValue(String.class);
                        String description =dataSnapshot.child(LawDatabaseContract.DocumentEntry.DESCRIPTION_ARM).getValue(String.class);
                        int view = dataSnapshot.child(LawDatabaseContract.DocumentEntry.VIEW_ARM).getValue(int.class);
                        doc = new DocumentMostView(id,url,title,description,view);
                        listDocumentView.add(doc);
                    }
                    Log.d("firebase", "onDataChange: ");
                }else {
                    Log.d("firebase", "onDataChange: ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("firebase", "onDataChange: ");
            }
        });

    }
}