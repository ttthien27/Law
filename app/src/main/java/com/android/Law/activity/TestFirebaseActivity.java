package com.android.Law.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.Law.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TestFirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_firebase);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://appphapluat-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello World!");
    }
}